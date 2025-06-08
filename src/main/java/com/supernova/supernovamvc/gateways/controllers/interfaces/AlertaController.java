package com.supernova.supernovamvc.gateways.controllers.interfaces;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.alerta.AlertaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.alerta.AlertaRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AlertaController {
    ResponseEntity<AlertaResponseDTO> create(AlertaRequestDTO dto);
    ResponseEntity<AlertaResponseDTO> update(String id, AlertaRequestDTO dto);
    ResponseEntity<Void> delete(String id);
    ResponseEntity<AlertaResponseDTO> findById(String id);
    ResponseEntity<List<AlertaResponseDTO>> findAll();
}
