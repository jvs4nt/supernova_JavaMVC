package com.supernova.supernovamvc.gateways.controllers.interfaces;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.UsuarioResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.usuarios.UsuarioRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioController {

    ResponseEntity<UsuarioResponseDTO> criar(UsuarioRequestDTO dto);
    ResponseEntity<UsuarioResponseDTO> atualizar(String id, UsuarioRequestDTO dto);
    ResponseEntity<Void> deletar(String id);
    ResponseEntity<UsuarioResponseDTO> buscarPorId(String id);
    ResponseEntity<List<UsuarioResponseDTO>> listarTodos();
    ResponseEntity<UsuarioResponseDTO> bloquear(String id);
    ResponseEntity<UsuarioResponseDTO> aprovarDocumento(String id);
}