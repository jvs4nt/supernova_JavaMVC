package com.supernova.supernovamvc.usecases.domains.implementations;

import com.supernova.supernovamvc.domains.Noticia;
import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.gateways.dtos.reponses.domains.noticia.NoticiaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.noticia.NoticiaRequestDTO;
import com.supernova.supernovamvc.usecases.services.query.NoticiaQueryService;
import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoticiaServiceImplTest {

    @Mock
    private NoticiaQueryService noticiaQueryService;

    @Mock
    private UsuarioQueryService usuarioQueryService;

    @InjectMocks
    private NoticiaServiceImpl noticiaService;

    private Usuario mockUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId("usuario-id");
        usuario.setNome("Admin");
        usuario.setEmail("admin@teste.com");
        return usuario;
    }

    @Test
    void testSalvar() {
        NoticiaRequestDTO dto = new NoticiaRequestDTO();
        dto.setTitulo("Titulo");
        dto.setSubtitulo("Sub");
        dto.setConteudo("Conteudo");
        dto.setLink("Link");
        dto.setUsuarioId("usuario-id");

        Usuario autor = mockUsuario();

        Noticia noticia = new Noticia();
        noticia.setTitulo("Titulo");
        noticia.setUsuario(autor);

        when(usuarioQueryService.findByIdOrThrow("usuario-id")).thenReturn(autor);
        when(noticiaQueryService.save(any(Noticia.class))).thenReturn(noticia);

        NoticiaResponseDTO response = noticiaService.salvar(dto);

        assertEquals("Titulo", response.getTitulo());
        verify(usuarioQueryService).findByIdOrThrow("usuario-id");
        verify(noticiaQueryService).save(any(Noticia.class));
    }

    @Test
    void testAtualizar() {
        Noticia existente = new Noticia();
        existente.setId("1");
        existente.setTitulo("Antigo");
        existente.setUsuario(mockUsuario());

        NoticiaRequestDTO dto = new NoticiaRequestDTO();
        dto.setTitulo("Novo");

        when(noticiaQueryService.findByIdOrThrow("1")).thenReturn(existente);
        when(noticiaQueryService.save(any(Noticia.class))).thenReturn(existente);

        NoticiaResponseDTO response = noticiaService.atualizar("1", dto);

        assertEquals("Novo", response.getTitulo());
        verify(noticiaQueryService).findByIdOrThrow("1");
        verify(noticiaQueryService).save(any(Noticia.class));
    }

    @Test
    void testDeletar() {
        noticiaService.deletar("1");
        verify(noticiaQueryService).deleteById("1");
    }

    @Test
    void testBuscarPorId() {
        Noticia noticia = new Noticia();
        noticia.setId("1");
        noticia.setTitulo("Notícia Teste");
        noticia.setUsuario(mockUsuario());

        when(noticiaQueryService.findByIdOrThrow("1")).thenReturn(noticia);

        NoticiaResponseDTO response = noticiaService.buscarPorId("1");

        assertEquals("1", response.getId());
        assertEquals("Notícia Teste", response.getTitulo());
        verify(noticiaQueryService).findByIdOrThrow("1");
    }

    @Test
    void testListarTodas() {
        Noticia noticia1 = new Noticia();
        noticia1.setId("1");
        noticia1.setTitulo("Notícia 1");
        noticia1.setUsuario(mockUsuario());

        Noticia noticia2 = new Noticia();
        noticia2.setId("2");
        noticia2.setTitulo("Notícia 2");
        noticia2.setUsuario(mockUsuario());

        List<Noticia> noticias = List.of(noticia1, noticia2);

        when(noticiaQueryService.findAll()).thenReturn(noticias);

        List<NoticiaResponseDTO> response = noticiaService.listarTodas();

        assertEquals(2, response.size());
        verify(noticiaQueryService).findAll();
    }
}
