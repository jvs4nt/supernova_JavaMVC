package com.supernova.supernovamvc.gateways.controllers.implementations;

import com.supernova.supernovamvc.gateways.controllers.interfaces.NoticiaController;
import com.supernova.supernovamvc.gateways.dtos.reponses.domains.noticia.NoticiaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.noticia.NoticiaRequestDTO;
import com.supernova.supernovamvc.usecases.domains.interfaces.NoticiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/noticias")
@Tag(name = "Notícia")
public class NoticiaControllerImpl implements NoticiaController {

    private final NoticiaService noticiaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar nova notícia")
    @Override
    public ResponseEntity<NoticiaResponseDTO> create(@Valid @RequestBody NoticiaRequestDTO dto) {
        return ResponseEntity.ok(noticiaService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar notícia existente")
    @Override
    public ResponseEntity<NoticiaResponseDTO> update(@PathVariable String id,
                                                     @Valid @RequestBody NoticiaRequestDTO dto) {
        return ResponseEntity.ok(noticiaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar notícia")
    @Override
    public ResponseEntity<Void> delete(@PathVariable String id) {
        noticiaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar notícia por ID")
    @Override
    public ResponseEntity<NoticiaResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(noticiaService.buscarPorId(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar todas as notícias")
    @Override
    public ResponseEntity<List<NoticiaResponseDTO>> findAll() {
        return ResponseEntity.ok(noticiaService.listarTodas());
    }
}
