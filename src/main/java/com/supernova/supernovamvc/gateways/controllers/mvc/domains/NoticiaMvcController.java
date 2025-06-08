package com.supernova.supernovamvc.gateways.controllers.mvc.domains;

import com.supernova.supernovamvc.domains.Usuario;
import com.supernova.supernovamvc.gateways.dtos.requests.domains.noticia.NoticiaRequestDTO;
import com.supernova.supernovamvc.usecases.domains.interfaces.NoticiaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/noticias")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class NoticiaMvcController {

    private final NoticiaService noticiaService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("noticias", noticiaService.listarTodas());
        return "domains/noticias/noticias";
    }

    @GetMapping("/nova")
    public String novaForm(Model model, @SessionAttribute("usuarioLogado") Usuario usuarioLogado) {
        model.addAttribute("noticia", new NoticiaRequestDTO());
        model.addAttribute("autorNome", usuarioLogado.getNome());
        return "domains/noticias/form-noticia";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        var noticia = noticiaService.buscarPorId(id);

        var dto = new NoticiaRequestDTO();
        dto.setTitulo(noticia.getTitulo());
        dto.setSubtitulo(noticia.getSubtitulo());
        dto.setConteudo(noticia.getConteudo());
        dto.setLink(noticia.getLink());

        model.addAttribute("noticia", dto);
        model.addAttribute("noticiaId", id);
        return "domains/noticias/form-noticia";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("noticia") NoticiaRequestDTO dto,
                         BindingResult result,
                         @RequestParam(required = false) String noticiaId,
                         @SessionAttribute("usuarioLogado") Usuario usuarioLogado,
                         RedirectAttributes redirectAttributes,
                         Model model) {

        if (result.hasErrors()) {
            model.addAttribute("noticiaId", noticiaId);
            return "domains/noticias/form-noticia";
        }

        dto.setUsuarioId(usuarioLogado.getId()); // Corrigido: ID é String

        if (noticiaId == null || noticiaId.isBlank()) {
            noticiaService.salvar(dto);
            redirectAttributes.addFlashAttribute("success", "Notícia criada com sucesso!");
        } else {
            noticiaService.atualizar(noticiaId, dto);
            redirectAttributes.addFlashAttribute("success", "Notícia atualizada com sucesso!");
        }

        return "redirect:/admin/noticias";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable String id, RedirectAttributes redirectAttributes) {
        noticiaService.deletar(id);
        redirectAttributes.addFlashAttribute("success", "Notícia deletada com sucesso!");
        return "redirect:/admin/noticias";
    }
}
