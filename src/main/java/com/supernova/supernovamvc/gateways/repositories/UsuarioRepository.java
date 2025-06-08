package com.supernova.supernovamvc.gateways.repositories;

import com.supernova.supernovamvc.domains.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByEmail(String email);


}
