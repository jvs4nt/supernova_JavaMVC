package com.supernova.supernovamvc.usecases.domains.implementations;

import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.UsuarioResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.usuarios.UsuarioRequestDTO;
import com.supernova.supernovamvc.gateways.mappers.domains.UsuarioMapper;
import com.supernova.supernovamvc.usecases.domains.interfaces.UsuarioService;
import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
import com.supernova.supernovamvc.usecases.services.queue.producers.UsuarioProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioQueryService usuarioQueryService;
    private final UsuarioProducer usuarioProducer;

    @Override
    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
        Usuario usuario = UsuarioMapper.toEntity(dto);
        Usuario saved = usuarioQueryService.save(usuario);
        UsuarioResponseDTO response = UsuarioMapper.toResponse(saved);
        usuarioProducer.notificarAutenticacao(saved.getId());
        return response;
    }

    @Override
    public List<UsuarioResponseDTO> listarTodos() {
        return UsuarioMapper.toResponseList(usuarioQueryService.findAll());
    }

    @Override
    public UsuarioResponseDTO buscarPorId(String id) {
        Usuario usuario = usuarioQueryService.findByIdOrThrow(id);
        return UsuarioMapper.toResponse(usuario);
    }

    @Override
    public UsuarioResponseDTO atualizar(String id, UsuarioRequestDTO dto) {
        Usuario usuario = usuarioQueryService.findByIdOrThrow(id);
        UsuarioMapper.updateEntity(usuario, dto);
        Usuario saved = usuarioQueryService.save(usuario);
        UsuarioResponseDTO response = UsuarioMapper.toResponse(saved);
        usuarioProducer.notificarAutenticacao(saved.getId());
        return response;
    }

    @Override
    public void deletar(String id) {
        usuarioQueryService.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO bloquear(String id) {
        Usuario usuario = usuarioQueryService.findByIdOrThrow(id);
        usuario.setFlDocVerificado("N");
        Usuario saved = usuarioQueryService.save(usuario);
        UsuarioResponseDTO response = UsuarioMapper.toResponse(saved);
        usuarioProducer.notificarAutenticacao(saved.getId());
        return response;
    }

    @Override
    public UsuarioResponseDTO aprovarDocumento(String id) {
        Usuario usuario = usuarioQueryService.findByIdOrThrow(id);
        usuario.setFlDocVerificado("S");
        Usuario saved = usuarioQueryService.save(usuario);
        UsuarioResponseDTO response = UsuarioMapper.toResponse(saved);
        usuarioProducer.notificarAutenticacao(saved.getId());
        return response;
    }
}
