package com.supernova.supernovamvc.gateways.dtos.requests.domains.usuarios;

import lombok.Builder;
import lombok.Data;


@Data
public class UsuarioRequestDTO {
    private String nome;
    private String email;
    private String senha;
    private String cep;
    private String role;
}