package com.supernova.supernovamvc.gateways.dtos.reponses.domains.documentos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentoExtraidoDTO {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String nacionalidade;
}
