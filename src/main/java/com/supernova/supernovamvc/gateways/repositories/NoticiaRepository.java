package com.supernova.supernovamvc.gateways.repositories;

import com.supernova.supernovamvc.domains.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticiaRepository extends JpaRepository<Noticia, String> {
}
