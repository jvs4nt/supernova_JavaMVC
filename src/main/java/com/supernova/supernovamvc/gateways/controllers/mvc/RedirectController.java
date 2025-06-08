package com.supernova.supernovamvc.gateways.controllers.mvc;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RedirectController {

    @GetMapping("/redirect")
    public String redirectAfterLogin(Authentication authentication, HttpServletRequest request) {
        if (authentication != null &&
                authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/usuarios";
        }


        request.getSession().setAttribute("error", "Acesso restrito. Apenas administradores podem acessar esta área.");
        return "redirect:/login?error";
    }
}