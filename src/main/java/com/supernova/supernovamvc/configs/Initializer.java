package com.supernova.supernovamvc.configs;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.UsuarioResponseDTO;
import com.supernova.supernovamvc.usecases.services.query.NoticiaQueryService;
import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
import com.supernova.supernovamvc.usecases.services.queue.producers.UsuarioProducer;
import com.supernova.supernovamvc.usecases.services.setup.SetupDatabase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Initializer {

    private final SetupDatabase setupDatabase;
    private final UsuarioQueryService usuarioQueryService;
    private final UsuarioProducer usuarioProducer;

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        setupDatabase.initializeDatabase();

        var admin = usuarioQueryService.findByEmailOrNull("admin@supernova.com");
        if (admin != null) {
            usuarioProducer.notificarAutenticacao(String.valueOf(admin.getId()));
        }

    }
}
