package com.supernova.supernovamvc.usecases.services.query;

import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.gateways.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioQueryServiceTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioQueryService usuarioQueryService;

    @BeforeEach
    void setUp() {
        usuarioRepository = mock(UsuarioRepository.class);
        usuarioQueryService = new UsuarioQueryService(usuarioRepository);
    }

    @Test
    void testFindByIdOrThrow_whenFound() {
        Usuario usuario = new Usuario();
        usuario.setId("1");

        when(usuarioRepository.findById("1")).thenReturn(Optional.of(usuario));

        Usuario result = usuarioQueryService.findByIdOrThrow("1");

        assertEquals("1", result.getId());
        verify(usuarioRepository).findById("1");
    }

    @Test
    void testFindByIdOrThrow_whenNotFound() {
        when(usuarioRepository.findById("999")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> usuarioQueryService.findByIdOrThrow("999"));
    }

    @Test
    void testFindAll() {
        List<Usuario> usuarios = List.of(new Usuario(), new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioQueryService.findAll();

        assertEquals(2, result.size());
        verify(usuarioRepository).findAll();
    }
}
