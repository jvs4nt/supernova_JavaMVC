package com.supernova.supernovamvc.gateways.dtos.reponses.domains.noticia;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoticiaResponseDTO {
    private String id;
    private String titulo;
    private String subtitulo;
    private String conteudo;
    private String link;
    private String usuarioId;
    private String nomeAutor;
}