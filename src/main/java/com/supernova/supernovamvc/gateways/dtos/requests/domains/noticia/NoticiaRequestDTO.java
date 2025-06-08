package com.supernova.supernovamvc.gateways.dtos.requests.domains.noticia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class NoticiaRequestDTO {

    private String usuarioId;

    @NotBlank(message = "O título é obrigatório.")
    @Size(max = 200, message = "O título deve ter no máximo 200 caracteres.")
    private String titulo;

    @Size(max = 200, message = "O subtítulo deve ter no máximo 200 caracteres.")
    private String subtitulo;

    @Size(max = 1000, message = "O conteúdo deve ter no máximo 1000 caracteres.")
    private String conteudo;

    @Size(max = 500, message = "O link deve ter no máximo 500 caracteres.")
    private String link;
}