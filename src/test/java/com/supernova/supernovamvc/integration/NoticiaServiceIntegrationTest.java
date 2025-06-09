//package com.supernova.supernovamvc.integration;
//
//import com.supernova.supernovamvc.domains.Usuario;
//import com.supernova.supernovamvc.gateways.dtos.reponses.domains.noticia.NoticiaResponseDTO;
//import com.supernova.supernovamvc.gateways.dtos.requests.domains.noticia.NoticiaRequestDTO;
//import com.supernova.supernovamvc.usecases.domains.interfaces.NoticiaService;
//import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@TestPropertySource(locations = "classpath:application-test.yml")
//public class NoticiaServiceIntegrationTest {
//
//    @Autowired
//    private NoticiaService noticiaService;
//
//    @Autowired
//    private UsuarioQueryService usuarioQueryService;
//
//    private String usuarioId;
//
//    @BeforeAll
//    void setup() {
//        Usuario usuario = Usuario.builder()
//                .nome("Admin Teste")
//                .email("admin@teste.com")
//                .senha("senha123")
//                .role("ADMIN")
//                .flDocVerificado("S")
//                .cep("01234-000")
//                .build();
//
//        usuarioId = usuarioQueryService.save(usuario).getId();
//    }
//
//    @Test
//    void deveSalvarENoticiaERecuperar() {
//        NoticiaRequestDTO dto = new NoticiaRequestDTO();
//        dto.setUsuarioId(usuarioId);
//        dto.setTitulo("Título de Teste");
//        dto.setSubtitulo("Subtitulo de Teste");
//        dto.setConteudo("Conteúdo da notícia de teste.");
//        dto.setLink("http://link.com");
//
//        NoticiaResponseDTO salva = noticiaService.salvar(dto);
//        assertNotNull(salva.getId());
//        assertEquals("Título de Teste", salva.getTitulo());
//
//        NoticiaResponseDTO encontrada = noticiaService.buscarPorId(salva.getId());
//        assertEquals(salva.getId(), encontrada.getId());
//        assertEquals("Admin Teste", encontrada.getNomeAutor());
//    }
//}