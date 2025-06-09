//package com.supernova.supernovamvc.usecases.services.queue;
//
//import com.supernova.supernovamvc.domains.Usuario;
//import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.AutenticacaoUsuarioDTO;
//import com.supernova.supernovamvc.gateways.repositories.UsuarioRepository;
//import com.supernova.supernovamvc.usecases.services.queue.consumers.UsuarioConsumer;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//class UsuarioConsumerTest {
//
//    private UsuarioRepository usuarioRepository;
//    private UsuarioConsumer usuarioConsumer;
//
//    @BeforeEach
//    void setup() {
//        usuarioRepository = mock(UsuarioRepository.class);
//        usuarioConsumer = new UsuarioConsumer(usuarioRepository);
//    }
//
//    @Test
//    void deveProcessarMensagemEAprovarUsuario() {
//        // Arrange
//        String userId = "123";
//        Usuario usuario = new Usuario();
//        usuario.setId(userId);
//        usuario.setFlDocVerificado("N");
//
//        AutenticacaoUsuarioDTO dto = AutenticacaoUsuarioDTO.builder()
//                .usuarioId(userId)
//                .build();
//
//        when(usuarioRepository.findById(userId)).thenReturn(Optional.of(usuario));
//
//        // Act
//        usuarioConsumer.processarAutenticacao(dto);
//
//        // Assert
//        verify(usuarioRepository).findById(userId);
//        verify(usuarioRepository).save(usuario);
//        assert "S".equals(usuario.getFlDocVerificado());
//    }
//
//    @Test
//    void naoDeveSalvarUsuarioSeNaoEncontrado() {
//        String userId = "999";
//        AutenticacaoUsuarioDTO dto = AutenticacaoUsuarioDTO.builder().usuarioId(userId).build();
//
//        when(usuarioRepository.findById(userId)).thenReturn(Optional.empty());
//
//        usuarioConsumer.processarAutenticacao(dto);
//
//        verify(usuarioRepository, never()).save(any());
//    }
//}
//
