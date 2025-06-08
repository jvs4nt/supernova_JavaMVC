package com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AutenticacaoUsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String usuarioId;
}