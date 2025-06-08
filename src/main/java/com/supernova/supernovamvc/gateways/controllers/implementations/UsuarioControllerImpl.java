package com.supernova.supernovamvc.gateways.controllers.implementations;

import com.supernova.supernovamvc.gateways.controllers.interfaces.UsuarioController;
import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.UsuarioResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.usuarios.UsuarioRequestDTO;
import com.supernova.supernovamvc.usecases.domains.interfaces.UsuarioService;
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
@RequestMapping("/api/admin/usuarios")
@Tag(name = "Usuário")
public class UsuarioControllerImpl implements UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar novo usuário")
    @Override
    public ResponseEntity<UsuarioResponseDTO> criar(@Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar usuário")
    @Override
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable String id,
                                                        @Valid @RequestBody UsuarioRequestDTO dto) {
        return ResponseEntity.ok(usuarioService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar usuário")
    @Override
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        usuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar usuário por ID")
    @Override
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar todos os usuários")
    @Override
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @PatchMapping("/{id}/bloquear")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Bloquear usuário")
    @Override
    public ResponseEntity<UsuarioResponseDTO> bloquear(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.bloquear(id));
    }

    @PatchMapping("/{id}/aprovar")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Aprovar documento do usuário")
    @Override
    public ResponseEntity<UsuarioResponseDTO> aprovarDocumento(@PathVariable String id) {
        return ResponseEntity.ok(usuarioService.aprovarDocumento(id));
    }
}
