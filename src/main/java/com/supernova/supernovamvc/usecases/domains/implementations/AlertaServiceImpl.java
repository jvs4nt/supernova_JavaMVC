package com.supernova.supernovamvc.usecases.domains.implementations;

import com.supernova.supernovamvc.domains.Alerta;
import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.gateways.dtos.reponses.domains.alerta.AlertaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.alerta.AlertaRequestDTO;
import com.supernova.supernovamvc.gateways.mappers.domains.AlertaMapper;
import com.supernova.supernovamvc.usecases.domains.interfaces.AlertaService;
import com.supernova.supernovamvc.usecases.services.query.AlertaQueryService;
import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaServiceImpl implements AlertaService {

    private final AlertaQueryService alertaQueryService;
    private final UsuarioQueryService usuarioQueryService;

    @Override
    public AlertaResponseDTO salvar(AlertaRequestDTO dto) {
        Usuario usuario = usuarioQueryService.findByIdOrThrow(String.valueOf(dto.getUsuarioId()));
        Alerta alerta = AlertaMapper.toEntity(dto, usuario);
        return AlertaMapper.toResponse(alertaQueryService.save(alerta));
    }

    @Override
    public AlertaResponseDTO atualizar(String id, AlertaRequestDTO dto) {
        Alerta alerta = alertaQueryService.findByIdOrThrow(id);
        AlertaMapper.updateEntity(alerta, dto);
        return AlertaMapper.toResponse(alertaQueryService.save(alerta));
    }

    @Override
    public void deletar(String id) {
        alertaQueryService.deleteById(id);
    }

    @Override
    public AlertaResponseDTO buscarPorId(String id) {
        return AlertaMapper.toResponse(alertaQueryService.findByIdOrThrow(id));
    }

    @Override
    public List<AlertaResponseDTO> listarTodos() {
        return AlertaMapper.toResponseList(alertaQueryService.findAll());
    }
}
