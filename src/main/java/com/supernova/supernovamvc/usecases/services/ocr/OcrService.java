package com.supernova.supernovamvc.usecases.services.ocr;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.documentos.DocumentoExtraidoDTO;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OcrService {

    private final Tesseract tesseract;

    public OcrService() {
        this.tesseract = new Tesseract();
        this.tesseract.setDatapath("tessdata");
        this.tesseract.setLanguage("por");
    }

    public DocumentoExtraidoDTO extrairDocumento(File imagem) {
        System.setProperty("jna.library.path", "/opt/homebrew/lib");

        try {
            String texto = tesseract.doOCR(imagem);
            String nome = extrairCampo(texto, "Nome ?/ ?Name\\s*([A-ZÀ-ÿ ]{5,40})");
            String cpf = extrairCampo(texto, "\\b(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2})\\b");
            String nascimento = extrairCampo(texto, "\\b(\\d{2}/\\d{2}/\\d{4})\\b");
            String nacionalidade = extrairCampo(texto, "Nacionalidade\\s*/\\s*Nationality\\s*(BRASILEIRA|ESTRANGEIRA)");

            if (nacionalidade == null) {
                nacionalidade = extrairCampo(texto, "(BRASILEIRA|ESTRANGEIRA)");
            }


            return DocumentoExtraidoDTO.builder()
                    .nome(nome)
                    .cpf(cpf)
                    .dataNascimento(nascimento)
                    .nacionalidade(nacionalidade)
                    .build();

        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String extrairCampo(String texto, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(texto);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return null;
    }
}
