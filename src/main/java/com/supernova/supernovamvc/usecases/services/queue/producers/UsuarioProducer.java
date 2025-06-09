package com.supernova.supernovamvc.usecases.services.queue.producers;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.AutenticacaoUsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioProducer {

    public void notificarAutenticacao(String usuarioId) {
        AutenticacaoUsuarioDTO dto = AutenticacaoUsuarioDTO.builder()
                .usuarioId(usuarioId)
                .build();
    }
}
