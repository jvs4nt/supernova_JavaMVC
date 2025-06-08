package com.supernova.supernovamvc.usecases.services.queue.producers;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.AutenticacaoUsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.supernova.supernovamvc.configs.RabbitMQConfig.*;

@Service
@RequiredArgsConstructor
public class UsuarioProducer {

    private final RabbitTemplate rabbitTemplate;

    public void notificarAutenticacao(String usuarioId) {
        AutenticacaoUsuarioDTO dto = AutenticacaoUsuarioDTO.builder()
                .usuarioId(usuarioId)
                .build();
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, dto);
    }
}
