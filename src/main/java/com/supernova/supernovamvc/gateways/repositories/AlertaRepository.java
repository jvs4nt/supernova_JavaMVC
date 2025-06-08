package com.supernova.supernovamvc.gateways.repositories;

import com.supernova.supernovamvc.domains.Alerta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlertaRepository extends MongoRepository<Alerta, String> {
}
