package com.supernova.supernovamvc.gateways.controllers.mvc.domains;

import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.alerta.AlertaRequestDTO;
import com.supernova.supernovamvc.usecases.domains.interfaces.AlertaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/alertas")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AlertaMvcController {

    private final AlertaService alertaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("alertas", alertaService.listarTodos());
        return "domains/alertas/alertas";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("alerta", new AlertaRequestDTO());
        return "domains/alertas/form-alerta";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        var alerta = alertaService.buscarPorId(id);

        var dto = new AlertaRequestDTO();
        dto.setTipoAlerta(alerta.getTipoAlerta());
        dto.setLatitude(alerta.getLatitude());
        dto.setLongitude(alerta.getLongitude());

        model.addAttribute("alerta", dto);
        model.addAttribute("alertaId", id);
        return "domains/alertas/form-alerta";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("alerta") AlertaRequestDTO dto,
                         BindingResult result,
                         @RequestParam(required = false) String alertaId,
                         @SessionAttribute("usuarioLogado") Usuario usuarioLogado,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("alertaId", alertaId);
            return "domains/alertas/form-alerta";
        }

        dto.setUsuarioId(usuarioLogado.getId()); // ID como String

        if (alertaId == null || alertaId.isBlank()) {
            alertaService.salvar(dto);
            redirectAttributes.addFlashAttribute("success", "Alerta criado com sucesso!");
        } else {
            alertaService.atualizar(alertaId, dto);
            redirectAttributes.addFlashAttribute("success", "Alerta atualizado com sucesso!");
        }

        return "redirect:/admin/alertas";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable String id, RedirectAttributes redirectAttributes) {
        alertaService.deletar(id);
        redirectAttributes.addFlashAttribute("success", "Alerta deletado com sucesso!");
        return "redirect:/admin/alertas";
    }
}
