package com.supernova.supernovamvc.gateways.mappers.domains;



import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.gateways.dtos.reponses.domains.usuarios.UsuarioResponseDTO;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.usuarios.UsuarioRequestDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequestDTO dto) {
        return Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .cep(dto.getCep())
                .role(dto.getRole() != null ? dto.getRole() : "USER")
                .flDocVerificado("N") // Sempre começa como não verificado
                .build();
    }

    public static void updateEntity(Usuario usuario, UsuarioRequestDTO dto) {
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCep(dto.getCep());
        if (dto.getRole() != null) {
            usuario.setRole(dto.getRole());
        }
    }

    public static UsuarioResponseDTO toResponse(Usuario usuario) {
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .cep(usuario.getCep())
                .role(usuario.getRole())
                .flDocVerificado(usuario.getFlDocVerificado())
                .build();
    }

    public static List<UsuarioResponseDTO> toResponseList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
    }
}
