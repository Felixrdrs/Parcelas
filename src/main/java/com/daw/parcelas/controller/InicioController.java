package com.daw.parcelas.controller;

import com.daw.parcelas.service.ParcelaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    private final ParcelaService parcelaService;

    public InicioController(ParcelaService parcelaService) {
        this.parcelaService = parcelaService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute(
                "superficieTotal",
                parcelaService.superficieTotal());

        model.addAttribute(
                "totalParcelas",
                parcelaService.getAllParcels().size());

        return "inicio";
    }
}