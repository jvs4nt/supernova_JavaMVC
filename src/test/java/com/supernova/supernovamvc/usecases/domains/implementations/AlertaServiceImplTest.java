//package com.supernova.supernovamvc.usecases.domains.implementations;
//
//import com.supernova.supernovamvc.domains.Alerta;
//import com.supernova.supernovamvc.domains.Usuario;
//import com.supernova.supernovamvc.gateways.dtos.reponses.domains.alerta.AlertaResponseDTO;
//import com.supernova.supernovamvc.gateways.dtos.requests.domains.alerta.AlertaRequestDTO;
//import com.supernova.supernovamvc.usecases.services.query.AlertaQueryService;
//import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class AlertaServiceImplTest {
//
//    @Mock
//    private AlertaQueryService alertaQueryService;
//
//    @Mock
//    private UsuarioQueryService usuarioQueryService;
//
//    @InjectMocks
//    private AlertaServiceImpl alertaService;
//
//    @Test
//    void testSalvar() {
//        AlertaRequestDTO dto = new AlertaRequestDTO();
//        dto.setUsuarioId("user-id");
//        dto.setTipoAlerta("Inundação");
//        dto.setLatitude(10.5);
//        dto.setLongitude(20.5);
//
//        Usuario usuario = new Usuario();
//        usuario.setId(Long.valueOf("user-id"));
//
//        Alerta alerta = new Alerta();
//        alerta.setId(Long.valueOf("1"));
//        alerta.setTipoAlerta("Inundação");
//        alerta.setUsuario(usuario); // importante
//
//        when(usuarioQueryService.findByIdOrThrow("user-id")).thenReturn(usuario);
//        when(alertaQueryService.save(any(Alerta.class))).thenReturn(alerta);
//
//        AlertaResponseDTO response = alertaService.salvar(dto);
//
//        assertEquals("Inundação", response.getTipoAlerta());
//        assertEquals("user-id", response.getUsuarioId());
//        verify(usuarioQueryService).findByIdOrThrow("user-id");
//        verify(alertaQueryService).save(any(Alerta.class));
//    }
//
//    @Test
//    void testAtualizar() {
//        Usuario usuario = new Usuario();
//        usuario.setId(Long.valueOf("user-id"));
//
//        Alerta alerta = new Alerta();
//        alerta.setId(Long.valueOf("1"));
//        alerta.setTipoAlerta("Antigo");
//        alerta.setUsuario(usuario); // essencial
//
//        AlertaRequestDTO dto = new AlertaRequestDTO();
//        dto.setTipoAlerta("Novo");
//
//        when(alertaQueryService.findByIdOrThrow("1")).thenReturn(alerta);
//        when(alertaQueryService.save(any(Alerta.class))).thenReturn(alerta);
//
//        AlertaResponseDTO response = alertaService.atualizar("1", dto);
//
//        assertEquals("Novo", response.getTipoAlerta());
//        assertEquals("user-id", response.getUsuarioId());
//        verify(alertaQueryService).findByIdOrThrow("1");
//        verify(alertaQueryService).save(any(Alerta.class));
//    }
//
//    @Test
//    void testDeletar() {
//        alertaService.deletar("1");
//        verify(alertaQueryService).deleteById("1");
//    }
//
//    @Test
//    void testBuscarPorId() {
//        Usuario usuario = new Usuario();
//        usuario.setId(Long.valueOf("user-id"));
//
//        Alerta alerta = new Alerta();
//        alerta.setId(Long.valueOf("1"));
//        alerta.setTipoAlerta("Teste");
//        alerta.setUsuario(usuario); // necessário
//
//        when(alertaQueryService.findByIdOrThrow("1")).thenReturn(alerta);
//
//        AlertaResponseDTO response = alertaService.buscarPorId("1");
//
//        assertEquals("1", response.getId());
//        assertEquals("Teste", response.getTipoAlerta());
//        assertEquals("user-id", response.getUsuarioId());
//        verify(alertaQueryService).findByIdOrThrow("1");
//    }
//
//    @Test
//    void testListarTodos() {
//        Usuario usuario = new Usuario();
//        usuario.setId(Long.valueOf("user-id"));
//
//        Alerta alerta1 = new Alerta();
//        alerta1.setId(Long.valueOf("1"));
//        alerta1.setUsuario(usuario);
//
//        Alerta alerta2 = new Alerta();
//        alerta2.setId(Long.valueOf("2"));
//        alerta2.setUsuario(usuario);
//
//        List<Alerta> lista = List.of(alerta1, alerta2);
//        when(alertaQueryService.findAll()).thenReturn(lista);
//
//        List<AlertaResponseDTO> resposta = alertaService.listarTodos();
//
//        assertEquals(2, resposta.size());
//        assertEquals("user-id", resposta.get(0).getUsuarioId());
//        verify(alertaQueryService).findAll();
//    }
//}
