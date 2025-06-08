package com.supernova.supernovamvc.usecases.services.query;

import com.supernova.supernovamvc.domains.Noticia;
import com.supernova.supernovamvc.gateways.repositories.NoticiaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticiaQueryService {

    private final NoticiaRepository noticiaRepository;

    public Noticia save(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public Noticia findByIdOrThrow(String id) {
        return noticiaRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Notícia não encontrada"));
    }

    public List<Noticia> findAll() {
        return noticiaRepository.findAll();
    }

    public void deleteById(String id) {
        noticiaRepository.deleteById(String.valueOf(id));
    }
}