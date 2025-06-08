package com.supernova.supernovamvc.usecases.domains.interfaces;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.UsuarioResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.usuarios.UsuarioRequestDTO;

import java.util.List;

public interface UsuarioService {

    UsuarioResponseDTO salvar(UsuarioRequestDTO dto);

    List<UsuarioResponseDTO> listarTodos();

    UsuarioResponseDTO buscarPorId(String id); // Alterado para String

    UsuarioResponseDTO atualizar(String id, UsuarioRequestDTO dto); // Alterado para String

    void deletar(String id); // Alterado para String

    UsuarioResponseDTO bloquear(String id); // Alterado para String

    UsuarioResponseDTO aprovarDocumento(String id); // Alterado para String
}
