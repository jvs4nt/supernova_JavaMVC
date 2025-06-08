package com.supernova.supernovamvc.gateways.controllers.interfaces;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.noticia.NoticiaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.noticia.NoticiaRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NoticiaController {
    ResponseEntity<NoticiaResponseDTO> create(NoticiaRequestDTO dto);
    ResponseEntity<NoticiaResponseDTO> update(String id, NoticiaRequestDTO dto);
    ResponseEntity<Void> delete(String id);
    ResponseEntity<NoticiaResponseDTO> findById(String id);
    ResponseEntity<List<NoticiaResponseDTO>> findAll();
}