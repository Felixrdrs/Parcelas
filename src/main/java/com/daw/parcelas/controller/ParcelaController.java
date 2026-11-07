
package com.daw.parcelas.controller;

import com.daw.parcelas.model.Parcela;
import com.daw.parcelas.model.TipoCultivo;
import com.daw.parcelas.service.ParcelaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/parcelas")
public class ParcelaController {

    private final ParcelaService parcelaService;

    public ParcelaController(com.daw.parcelas.service.ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    @GetMapping
    public String listar(Model model) {
        // Obtener las parcelas
        var parcelas = parcelaService.listarTodas();
        // Pasárselas a la vista
        model.addAttribute("parcelas", parcelas);
        return "parcelas/lista";
    }

    @GetMapping("/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        var parcela = parcelaService.buscarPorId(id);
        model.addAttribute("parcela", parcela);
        return "parcelas/detalle";
    }

    @GetMapping("/nueva")
    public String nueva(Model model) {
        model.addAttribute("parcela", new Parcela());
        model.addAttribute("tipos", TipoCultivo.values());
        return "parcelas/formulario";
    }

    @PostMapping
    public String guardar(@ModelAttribute Parcela parcela) {
        parcelaService.guardar(parcela);
        return "redirect:/parcelas";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {
        parcelaService.eliminar(id);
        return "redirect:/parcelas";
    }
}


