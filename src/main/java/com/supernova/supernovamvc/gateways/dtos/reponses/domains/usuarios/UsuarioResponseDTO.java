package com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UsuarioResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String nome;
    private String email;
    private String cep;
    private String role;
    private String flDocVerificado;
}
