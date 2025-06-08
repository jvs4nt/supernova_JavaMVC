package com.supernova.supernovamvc.gateways.mappers.domains;

import com.supernova.supernovamvc.domains.Noticia;
import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.gateways.dtos.reponses.domains.noticia.NoticiaResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.noticia.NoticiaRequestDTO;


import java.util.List;
import java.util.stream.Collectors;

public class NoticiaMapper {

    public static Noticia toEntity(NoticiaRequestDTO dto, Usuario usuario) {
        return Noticia.builder()
                .titulo(dto.getTitulo())
                .subtitulo(dto.getSubtitulo())
                .conteudo(dto.getConteudo())
                .link(dto.getLink())
                .usuario(usuario)
                .build();
    }

    public static void updateEntity(Noticia noticia, NoticiaRequestDTO dto) {
        noticia.setTitulo(dto.getTitulo());
        noticia.setSubtitulo(dto.getSubtitulo());
        noticia.setConteudo(dto.getConteudo());
        noticia.setLink(dto.getLink());
    }

    public static NoticiaResponseDTO toResponse(Noticia noticia) {
        return NoticiaResponseDTO.builder()
                .id(noticia.getId())
                .titulo(noticia.getTitulo())
                .subtitulo(noticia.getSubtitulo())
                .conteudo(noticia.getConteudo())
                .link(noticia.getLink())
                .usuarioId(noticia.getUsuario().getId())
                .nomeAutor(noticia.getUsuario().getNome())
                .build();
    }

    public static List<NoticiaResponseDTO> toResponseList(List<Noticia> noticias) {
        return noticias.stream()
                .map(NoticiaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
