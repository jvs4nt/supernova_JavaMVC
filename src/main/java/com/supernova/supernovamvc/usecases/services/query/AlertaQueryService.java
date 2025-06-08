package com.supernova.supernovamvc.usecases.services.query;
import com.supernova.supernovamvc.domains.Alerta;
import com.supernova.supernovamvc.gateways.repositories.AlertaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaQueryService {

    private final AlertaRepository alertaRepository;

    public Alerta save(Alerta alerta) {
        return alertaRepository.save(alerta);
    }

    public Alerta findByIdOrThrow(String id) {
        return alertaRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Alerta não encontrado"));
    }

    public List<Alerta> findAll() {
        return alertaRepository.findAll();
    }

    public void deleteById(String id) {
        alertaRepository.deleteById(String.valueOf(id));
    }
}
