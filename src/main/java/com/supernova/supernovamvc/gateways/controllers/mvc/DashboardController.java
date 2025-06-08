package com.supernova.supernovamvc.gateways.controllers.mvc;

import com.supernova.supernovamvc.domains.Usuario;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class DashboardController {

    @GetMapping("/admin/dashboard")
    public String exibirDashboard(@SessionAttribute("usuarioLogado") Usuario usuario, Model model) {
        model.addAttribute("nome", usuario.getNome());
        return "dashboard";
    }
}
