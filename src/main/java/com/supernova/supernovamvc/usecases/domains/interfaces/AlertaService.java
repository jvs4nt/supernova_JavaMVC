package com.supernova.supernovamvc.usecases.domains.interfaces;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.alerta.AlertaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.alerta.AlertaRequestDTO;

import java.util.List;

public interface AlertaService {
    AlertaResponseDTO salvar(AlertaRequestDTO dto);
    AlertaResponseDTO atualizar(String id, AlertaRequestDTO dto);
    void deletar(String id);
    AlertaResponseDTO buscarPorId(String id);
    List<AlertaResponseDTO> listarTodos();
}