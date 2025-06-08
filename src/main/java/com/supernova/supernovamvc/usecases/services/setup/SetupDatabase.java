package com.supernova.supernovamvc.usecases.services.setup;

import com.supernova.supernovamvc.domains.Alerta;
import com.supernova.supernovamvc.domains.Noticia;
import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.usecases.enums.Role;
import com.supernova.supernovamvc.usecases.services.query.AlertaQueryService;
import com.supernova.supernovamvc.usecases.services.query.NoticiaQueryService;
import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SetupDatabase {

    private final UsuarioQueryService usuarioQueryService;
    private final NoticiaQueryService noticiaQueryService;
    private final AlertaQueryService alertaQueryService;
    private final PasswordEncoder passwordEncoder;

    public void initializeDatabase() {
        Usuario admin = criarUsuarioAdmin();
        criarUsuarioComum();
        criarUsuarioVoluntario();
        criarNoticiasExemplo(admin);
        criarAlertasExemplo();
    }

    private Usuario criarUsuarioAdmin() {
        String emailAdmin = "admin@supernova.com";
        Usuario admin = usuarioQueryService.findByEmailOrNull(emailAdmin);
        if (admin == null) {
            admin = Usuario.builder()
                    .nome("Administrador")
                    .email(emailAdmin)
                    .senha(passwordEncoder.encode("supernova"))
                    .role(String.valueOf(Role.ADMIN))
                    .flDocVerificado("S")
                    .cep("00000000")
                    .build();
            admin = usuarioQueryService.save(admin);
            log.info("Usuário ADMIN criado com sucesso.");
        } else {
            log.info("Usuário ADMIN já existe.");
        }
        return admin;
    }

    private void criarUsuarioComum() {
        String email = "user@email.com";
        if (usuarioQueryService.findByEmailOrNull(email) == null) {
            Usuario user = Usuario.builder()
                    .nome("Usuário Comum")
                    .email(email)
                    .senha(passwordEncoder.encode("user"))
                    .role(String.valueOf(Role.USER))
                    .flDocVerificado("S")
                    .cep("11111111")
                    .build();
            usuarioQueryService.save(user);
            log.info("Usuário COMUM criado com sucesso.");
        }
    }

    private void criarUsuarioVoluntario() {
        String email = "voluntario@email.com";
        if (usuarioQueryService.findByEmailOrNull(email) == null) {
            Usuario voluntario = Usuario.builder()
                    .nome("Voluntário")
                    .email(email)
                    .senha(passwordEncoder.encode("voluntario"))
                    .role(String.valueOf(Role.VOLUNTARIO))
                    .flDocVerificado("S")
                    .cep("22222222")
                    .build();
            usuarioQueryService.save(voluntario);
            log.info("Usuário VOLUNTÁRIO criado com sucesso.");
        }
    }

    private void criarNoticiasExemplo(Usuario admin) {
        String titulo1 = "Evento extremo atinge litoral";
        String titulo2 = "Temperaturas batem recorde histórico";
        String titulo3 = "Frente fria chega ao sul";

        boolean noticia1Existe = noticiaQueryService.findAll()
                .stream()
                .anyMatch(n -> n.getTitulo().equalsIgnoreCase(titulo1));

        boolean noticia2Existe = noticiaQueryService.findAll()
                .stream()
                .anyMatch(n -> n.getTitulo().equalsIgnoreCase(titulo2));

        boolean noticia3Existe = noticiaQueryService.findAll()
                .stream()
                .anyMatch(n -> n.getTitulo().equalsIgnoreCase(titulo3));

        if (!noticia1Existe) {
            Noticia noticia1 = Noticia.builder()
                    .titulo(titulo1)
                    .subtitulo("Fortes chuvas causam alagamentos")
                    .conteudo("Na madrugada de ontem, uma tempestade intensa atingiu a costa causando estragos em diversas regiões.")
                    .link("https://exemplo.com/noticia1")
                    .usuario(admin)
                    .build();
            noticiaQueryService.save(noticia1);
            log.info("✅ Notícia 1 criada.");
        } else {
            log.info("ℹ️ Notícia 1 já existe.");
        }

        if (!noticia2Existe) {
            Noticia noticia2 = Noticia.builder()
                    .titulo(titulo2)
                    .subtitulo("Calor intenso no sudeste")
                    .conteudo("Com a onda de calor, cidades como São Paulo e Rio de Janeiro registraram temperaturas acima de 40°C.")
                    .link("https://exemplo.com/noticia2")
                    .usuario(admin)
                    .build();
            noticiaQueryService.save(noticia2);
            log.info("✅ Notícia 2 criada.");
        } else {
            log.info("ℹ️ Notícia 2 já existe.");
        }

        if (!noticia3Existe) {
            Noticia noticia3 = Noticia.builder()
                    .titulo(titulo3)
                    .subtitulo("Frente fria chega ao sul")
                    .conteudo("A chegada de uma frente fria provocou queda acentuada nas temperaturas em cidades do sul do país, como Porto Alegre e Curitiba.")
                    .link("https://exemplo.com/noticia3")
                    .usuario(admin)
                    .build();
            noticiaQueryService.save(noticia3);
            log.info("✅ Notícia 3 criada.");
        } else {
            log.info("ℹ️ Notícia 3 já existe.");
        }
    }


    public void criarAlertasExemplo() {
        Usuario admin = usuarioQueryService.findByEmailOrNull("admin@supernova.com");
        if (admin == null) {
            log.warn("⚠️ Usuário ADMIN não encontrado. Alertas não foram criados.");
            return;
        }

        criarAlertaSeNaoExistir("Enchente em área urbana", -23.5489, -46.6388, admin);
        criarAlertaSeNaoExistir("Deslizamento de terra", -22.9068, -43.1729, admin);
        criarAlertaSeNaoExistir("Foco de incêndio florestal", -3.1190, -60.0217, admin);
    }

    private void criarAlertaSeNaoExistir(String tipo, double lat, double lon, Usuario admin) {
        boolean existe = alertaQueryService.findAll().stream()
                .anyMatch(a -> a.getTipoAlerta().equalsIgnoreCase(tipo));

        if (!existe) {
            Alerta alerta = Alerta.builder()
                    .tipoAlerta(tipo)
                    .latitude(lat)
                    .longitude(lon)
                    .usuario(admin)
                    .build();
            alertaQueryService.save(alerta);
            log.info("✅ Alerta '{}' criado.", tipo);
        } else {
            log.info("ℹ️ Alerta '{}' já existe.", tipo);
        }
    }
}
