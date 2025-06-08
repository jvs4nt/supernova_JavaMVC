package com.supernova.supernovamvc.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String flDocVerificado;
    private String role;
    private String cep;
}
