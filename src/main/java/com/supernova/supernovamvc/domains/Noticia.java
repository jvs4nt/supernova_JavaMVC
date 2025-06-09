package com.supernova.supernovamvc.domains;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "noticia")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String titulo;
    private String subtitulo;
    private String conteudo;
    private String link;
}
