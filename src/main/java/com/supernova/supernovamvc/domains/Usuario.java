package com.supernova.supernovamvc.domains;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String flDocVerificado;
    private String role;
    private String cep;
}
