package com.supernova.supernovamvc.gateways.controllers.mvc.domains;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.documentos.DocumentoExtraidoDTO;
import com.supernova.supernovamvc.usecases.services.ocr.OcrService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class DocumentoMvcController {

    private final OcrService ocrService;

    @GetMapping("/admin/documentos/validar")
    public String mostrarFormulario() {
        return "domains/documentos/pre-validacao";
    }

    @PostMapping("/admin/documentos/validar")
    public String processarImagem(@RequestParam("arquivo") MultipartFile arquivo, Model model) {
        try {
            String extensao = arquivo.getOriginalFilename().substring(arquivo.getOriginalFilename().lastIndexOf('.'));
            File imagemTemp = File.createTempFile("doc-", extensao);

            try (var inputStream = arquivo.getInputStream()) {
                java.nio.file.Files.copy(
                        inputStream,
                        imagemTemp.toPath(),
                        java.nio.file.StandardCopyOption.REPLACE_EXISTING
                );
            }


            DocumentoExtraidoDTO resultado = ocrService.extrairDocumento(imagemTemp);
            imagemTemp.delete();

            model.addAttribute("resultado", resultado);
            return "domains/documentos/pre-validacao";

        } catch (IOException e) {
            model.addAttribute("erro", "Falha ao processar imagem: " + e.getMessage());
            return "domains/documentos/pre-validacao";
        }
    }
}

