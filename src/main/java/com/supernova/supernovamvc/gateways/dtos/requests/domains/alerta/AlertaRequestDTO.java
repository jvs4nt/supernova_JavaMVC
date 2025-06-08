package com.supernova.supernovamvc.gateways.dtos.requests.domains.alerta;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;


@Data
public class AlertaRequestDTO {

    private String usuarioId;

    @NotBlank(message = "O tipo de alerta é obrigatório.")
    private String tipoAlerta;
    private Double latitude;
    private Double longitude;
}