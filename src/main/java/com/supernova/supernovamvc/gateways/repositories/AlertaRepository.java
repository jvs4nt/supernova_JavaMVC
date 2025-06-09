package com.supernova.supernovamvc.gateways.repositories;

import com.supernova.supernovamvc.domains.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, String> {
}
