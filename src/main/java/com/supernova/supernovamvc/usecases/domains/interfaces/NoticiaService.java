package com.supernova.supernovamvc.usecases.domains.interfaces;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.noticia.NoticiaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.noticia.NoticiaRequestDTO;

import java.util.List;

public interface NoticiaService {
    NoticiaResponseDTO salvar(NoticiaRequestDTO dto);
    NoticiaResponseDTO atualizar(String id, NoticiaRequestDTO dto);
    void deletar(String id);
    NoticiaResponseDTO buscarPorId(String id);
    List<NoticiaResponseDTO> listarTodas();
}
