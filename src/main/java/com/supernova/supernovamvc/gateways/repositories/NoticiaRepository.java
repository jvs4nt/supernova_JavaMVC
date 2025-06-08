package com.supernova.supernovamvc.gateways.repositories;

import com.supernova.supernovamvc.domains.Noticia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticiaRepository extends MongoRepository<Noticia, String> {
}
