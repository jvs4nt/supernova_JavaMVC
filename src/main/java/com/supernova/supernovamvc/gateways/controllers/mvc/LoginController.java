package com.supernova.supernovamvc.gateways.controllers.mvc;

import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioQueryService usuarioQueryService;

    @GetMapping("/login-success")
    public String loginSuccess(Authentication authentication, HttpSession session) {
        String email = authentication.getName();
        Usuario usuario = usuarioQueryService.findByEmailOrThrow(email);

        session.setAttribute("usuarioLogado", usuario);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
}