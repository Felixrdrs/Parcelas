package com.daw.parcelas.controller;

import com.daw.parcelas.model.Parcela;
import com.daw.parcelas.model.TipoCultivo;
import com.daw.parcelas.service.ParcelaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/parcelas")
public class ParcelaController {

    private final ParcelaService parcelaService;

    public ParcelaController(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    @ModelAttribute("tipos")
    public TipoCultivo[] tipos() {
        return TipoCultivo.values();
    }

    @GetMapping
    public String listar(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) TipoCultivo cultivo,
            Model model) {

        model.addAttribute("parcelas",
                parcelaService.getParcel(q, cultivo));

        model.addAttribute("q", q);
        model.addAttribute("cultivoSel", cultivo);

        return "parcelas/lista";
    }

    @GetMapping({"/nuevo", "/nueva"})
    public String nueva(Model model) {
        model.addAttribute("parcela", new Parcela());
        return "parcelas/formulario";
    }

    @GetMapping("/{id}")
    public String detalle(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "parcela",
                parcelaService.getParcelById(id));

        return "parcelas/detalle";
    }

    @GetMapping("/{id}/editar")
    public String editar(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "parcela",
                parcelaService.getParcelById(id));

        return "parcelas/formulario";
    }

    @PostMapping
    public String crear(
            @Valid @ModelAttribute("parcela") Parcela parcela,
            BindingResult errores,
            RedirectAttributes flash) {

        if (errores.hasErrors()) {
            return "parcelas/formulario";
        }

        Parcela guardada = parcelaService.guardar(parcela);

        flash.addFlashAttribute(
                "mensaje",
                "Parcela «" + guardada.getNombre()
                        + "» creada correctamente");

        return "redirect:/parcelas";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @Valid @ModelAttribute("parcela") Parcela parcela,
            BindingResult errores,
            RedirectAttributes flash) {

        if (errores.hasErrors()) {
            parcela.setId(id);
            return "parcelas/formulario";
        }

        parcela.setId(id);

        Parcela guardada = parcelaService.guardar(parcela);

        flash.addFlashAttribute(
                "mensaje",
                "Parcela «" + guardada.getNombre()
                        + "» actualizada correctamente");

        return "redirect:/parcelas";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(
            @PathVariable Long id,
            RedirectAttributes flash) {

        Parcela parcela = parcelaService.getParcelById(id);

        parcelaService.eliminar(id);

        flash.addFlashAttribute(
                "mensaje",
                "Parcela «" + parcela.getNombre()
                        + "» eliminada correctamente");

        return "redirect:/parcelas";
    }
}


