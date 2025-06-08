package com.supernova.supernovamvc.gateways.controllers.mvc.domains;

import com.supernova.supernovamvc.gateways.dtos.requests.domains.usuarios.UsuarioRequestDTO;
import com.supernova.supernovamvc.usecases.domains.interfaces.UsuarioService;
import com.supernova.supernovamvc.usecases.services.query.UsuarioQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/usuarios")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UsuarioMvcController {

    private final UsuarioService usuarioService;
    private final UsuarioQueryService usuarioQueryService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "domains/usuarios/usuarios";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("usuario", new UsuarioRequestDTO());
        return "domains/usuarios/form-usuario";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        var usuario = usuarioQueryService.findByIdOrThrow(id);

        var dto = new UsuarioRequestDTO();
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setCep(usuario.getCep());
        dto.setRole(usuario.getRole());
        dto.setSenha(usuario.getSenha());

        model.addAttribute("usuario", dto);
        model.addAttribute("usuarioId", id);
        return "domains/usuarios/form-usuario";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("usuario") UsuarioRequestDTO dto,
                         BindingResult result,
                         @RequestParam(required = false) String usuarioId,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("usuarioId", usuarioId);
            return "domains/usuarios/form-usuario";
        }

        if (usuarioId == null || usuarioId.isBlank()) {
            usuarioService.salvar(dto);
            redirectAttributes.addFlashAttribute("success", "Usuário criado com sucesso!");
        } else {
            usuarioService.atualizar(usuarioId, dto);
            redirectAttributes.addFlashAttribute("success", "Usuário atualizado com sucesso!");
        }

        return "redirect:/admin/usuarios";
    }

    @PostMapping("/{id}/bloquear")
    public String bloquear(@PathVariable String id, RedirectAttributes redirectAttributes) {
        usuarioService.bloquear(id);
        redirectAttributes.addFlashAttribute("success", "Usuário bloqueado com sucesso!");
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/{id}/aprovar")
    public String aprovar(@PathVariable String id, RedirectAttributes redirectAttributes) {
        usuarioService.aprovarDocumento(id);
        redirectAttributes.addFlashAttribute("success", "Documento aprovado com sucesso!");
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable String id, RedirectAttributes redirectAttributes) {
        usuarioService.deletar(id);
        redirectAttributes.addFlashAttribute("success", "Usuário deletado com sucesso!");
        return "redirect:/admin/usuarios";
    }
}
