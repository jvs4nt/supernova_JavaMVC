package com.supernova.supernovamvc.gateways.controllers.mvc.advices;

import com.supernova.supernovamvc.domains.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UsuarioSessionAdvice {

    @ModelAttribute("usuarioLogado")
    public Usuario usuarioLogado(HttpSession session) {
        return (Usuario) session.getAttribute("usuarioLogado");
    }
}