package com.supernova.supernovamvc.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "noticias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Noticia {
    @Id
    private String id;
    @DBRef
    private Usuario usuario;

    private String titulo;
    private String subtitulo;
    private String conteudo;
    private String link;
}
