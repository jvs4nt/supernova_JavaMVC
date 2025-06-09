package com.supernova.supernovamvc.gateways.repositories;

import com.supernova.supernovamvc.domains.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);


}
