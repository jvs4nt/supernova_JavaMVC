package com.supernova.supernovamvc.gateways.controllers.implementations;

import com.supernova.supernovamvc.gateways.controllers.interfaces.AlertaController;
import com.supernova.supernovamvc.gateways.dtos.reponses.domains.alerta.AlertaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.alerta.AlertaRequestDTO;
import com.supernova.supernovamvc.usecases.domains.interfaces.AlertaService;
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
@RequestMapping("/alerta")
@Tag(name = "Alerta")
public class AlertaControllerImpl implements AlertaController {

    private final AlertaService alertaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar novo alerta")
    @Override
    public ResponseEntity<AlertaResponseDTO> create(@RequestBody @Valid AlertaRequestDTO dto) {
        return ResponseEntity.ok(alertaService.salvar(dto));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar alerta existente")
    @Override
    public ResponseEntity<AlertaResponseDTO> update(@PathVariable String id,
                                                    @RequestBody @Valid AlertaRequestDTO dto) {
        return ResponseEntity.ok(alertaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar alerta")
    @Override
    public ResponseEntity<Void> delete(@PathVariable String id) {
        alertaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar alerta por ID")
    @Override
    public ResponseEntity<AlertaResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok(alertaService.buscarPorId(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar todos os alertas")
    @Override
    public ResponseEntity<List<AlertaResponseDTO>> findAll() {
        return ResponseEntity.ok(alertaService.listarTodos());
    }
}