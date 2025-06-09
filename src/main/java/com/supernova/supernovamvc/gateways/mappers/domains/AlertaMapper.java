package com.supernova.supernovamvc.gateways.mappers.domains;

import com.supernova.supernovamvc.domains.Alerta;
import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.gateways.dtos.reponses.domains.alerta.AlertaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.alerta.AlertaRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AlertaMapper {

    public static Alerta toEntity(AlertaRequestDTO dto, Usuario usuario) {
        return Alerta.builder()
                .usuario(usuario)
                .tipoAlerta(dto.getTipoAlerta())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .build();
    }

    public static void updateEntity(Alerta alerta, AlertaRequestDTO dto) {
        alerta.setTipoAlerta(dto.getTipoAlerta());
        alerta.setLatitude(dto.getLatitude());
        alerta.setLongitude(dto.getLongitude());
    }

    public static AlertaResponseDTO toResponse(Alerta alerta) {
        return AlertaResponseDTO.builder()
                .id(String.valueOf(alerta.getId()))
                .tipoAlerta(alerta.getTipoAlerta())
                .latitude(alerta.getLatitude())
                .longitude(alerta.getLongitude())
                .usuarioId(String.valueOf(alerta.getUsuario().getId()))
                .nomeUsuario(alerta.getUsuario().getNome())
                .build();
    }

    public static List<AlertaResponseDTO> toResponseList(List<Alerta> alertas) {
        return alertas.stream().map(AlertaMapper::toResponse).collect(Collectors.toList());
    }
}