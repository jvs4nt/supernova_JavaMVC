//package com.supernova.supernovamvc.usecases.domains.implementations;
//
//import com.supernova.supernovamvc.domains.Usuario;
//import com.supernova.supernovamvc.gateways.dtos.requests.domains.usuarios.UsuarioRequestDTO;
//import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.UsuarioResponseDTO;
//import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
//import com.supernova.supernovamvc.usecases.services.queue.producers.UsuarioProducer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class UsuarioServiceImplTest {
//
//    @Mock
//    private UsuarioQueryService usuarioQueryService;
//
//    @Mock
//    private UsuarioProducer usuarioProducer;
//
//    @InjectMocks
//    private UsuarioServiceImpl usuarioService;
//
//    @Test
//    void testSalvar() {
//        UsuarioRequestDTO dto = new UsuarioRequestDTO();
//        dto.setNome("Teste");
//        dto.setEmail("teste@email.com");
//
//        Usuario usuario = new Usuario();
//        usuario.setId(Long.valueOf("123"));
//        usuario.setNome("Teste");
//        usuario.setEmail("teste@email.com");
//
//        when(usuarioQueryService.save(any())).thenReturn(usuario);
//
//        UsuarioResponseDTO response = usuarioService.salvar(dto);
//
//        assertEquals("Teste", response.getNome());
//        assertEquals("teste@email.com", response.getEmail());
//        verify(usuarioQueryService).save(any());
//        verify(usuarioProducer).notificarAutenticacao("123");
//    }
//}
