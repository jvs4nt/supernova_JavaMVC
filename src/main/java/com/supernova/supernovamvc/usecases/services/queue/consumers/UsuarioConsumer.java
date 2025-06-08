package com.supernova.supernovamvc.usecases.services.queue.consumers;

import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.AutenticacaoUsuarioDTO;
import com.supernova.supernovamvc.gateways.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.supernova.supernovamvc.configs.RabbitMQConfig.QUEUE;

@Component
@Slf4j
@RequiredArgsConstructor
public class UsuarioConsumer {

    private final UsuarioRepository usuarioRepository;

    @RabbitListener(queues = QUEUE)
    public void processarAutenticacao(AutenticacaoUsuarioDTO dto) {
        log.info("📩 Autenticação recebida na fila para usuário ID: {}", dto.getUsuarioId());

        usuarioRepository.findById(dto.getUsuarioId()).ifPresent(usuario -> {
            usuario.setFlDocVerificado("S");
            usuarioRepository.save(usuario);
            log.info("✅ Usuário {} marcado como AUTENTICADO com sucesso!", dto.getUsuarioId());
        });
    }
}
