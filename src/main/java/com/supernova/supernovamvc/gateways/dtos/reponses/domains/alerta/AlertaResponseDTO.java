package com.supernova.supernovamvc.gateways.dtos.reponses.domains.alerta;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlertaResponseDTO {
    private String id;
    private String tipoAlerta;
    private Double latitude;
    private Double longitude;
    private String usuarioId;
    private String nomeUsuario;
}