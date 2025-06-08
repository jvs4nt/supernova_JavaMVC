package com.supernova.supernovamvc.usecases.domains.implementations;

import com.supernova.supernovamvc.domains.Noticia;
import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.gateways.dtos.reponses.domains.noticia.NoticiaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.noticia.NoticiaRequestDTO;
import com.supernova.supernovamvc.gateways.mappers.domains.NoticiaMapper;
import com.supernova.supernovamvc.usecases.domains.interfaces.NoticiaService;
import com.supernova.supernovamvc.usecases.services.query.NoticiaQueryService;
import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticiaServiceImpl implements NoticiaService {

    private final NoticiaQueryService noticiaQueryService;
    private final UsuarioQueryService usuarioQueryService;

    @Override
    public NoticiaResponseDTO salvar(NoticiaRequestDTO dto) {
        Noticia noticia = criarNoticiaComUsuario(dto);
        Noticia salva = noticiaQueryService.save(noticia);
        return NoticiaMapper.toResponse(salva);
    }

    @Override
    public NoticiaResponseDTO atualizar(String id, NoticiaRequestDTO dto) {
        Noticia existente = noticiaQueryService.findByIdOrThrow(id);
        NoticiaMapper.updateEntity(existente, dto); // Atualiza apenas os campos da notícia
        Noticia atualizada = noticiaQueryService.save(existente);
        return NoticiaMapper.toResponse(atualizada);
    }

    @Override
    public void deletar(String id) {
        noticiaQueryService.deleteById(id);
    }

    @Override
    public NoticiaResponseDTO buscarPorId(String id) {
        Noticia noticia = noticiaQueryService.findByIdOrThrow(id);
        return NoticiaMapper.toResponse(noticia);
    }

    @Override
    public List<NoticiaResponseDTO> listarTodas() {
        List<Noticia> noticias = noticiaQueryService.findAll();
        return NoticiaMapper.toResponseList(noticias);
    }

    private Noticia criarNoticiaComUsuario(NoticiaRequestDTO dto) {
        Usuario autor = usuarioQueryService.findByIdOrThrow(String.valueOf(dto.getUsuarioId()));
        return NoticiaMapper.toEntity(dto, autor);
    }
}
