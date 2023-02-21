package com.example.controllers;

import com.example.entities.Manufacturer;
import com.example.services.ManufacturerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @GetMapping("manufacturers")
    public String findAll(Model model) {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturer/manufacturer-list";
    }

    @GetMapping("manufacturers/{id}")
    public String findById(Model model, @PathVariable Long id) {
        Optional<Manufacturer> manufacturerOpt = manufacturerService.findById(id);
        if (manufacturerOpt.isPresent())
            model.addAttribute("manufacturer", manufacturerOpt.get());
        else
            model.addAttribute("error", "Manufacturer not found");

        return "manufacturer/manufacturer-detail";
    }

    @GetMapping("manufacturers/create")
    public String createForm(Model model) {
        model.addAttribute("manufacturer", new Manufacturer());
        return "manufacturer/manufacturer-form";
    }

    @GetMapping("manufacturers/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        Optional<Manufacturer> manufacturerOpt = manufacturerService.findById(id);
        if (manufacturerOpt.isPresent()) {
            model.addAttribute("manufacturer", manufacturerOpt.get());
        } else {
            model.addAttribute("error", "Manufacturer not found");
        }

        return "manufacturer/manufacturer-form";
    }

    @PostMapping("manufacturers")
    public String saveForm(@ModelAttribute Manufacturer manufacturer) {
        manufacturerService.save(manufacturer);
        return "redirect:/manufacturers";
    }

    @GetMapping("manufacturers/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        manufacturerService.deleteById(id);
        return "redirect:/manufacturers";
    }
}
