package com.example.controllers;

import com.example.entities.Customer;
import com.example.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller

public class CustomerController {

    private final CustomerService customerService;

    // Crear metodos y cada metodo irá mapeado a una URL http
    // Normalmente estos metodos devuelven String; que es el nombre del archivo html

    @GetMapping("/customers") // Puede ser getmapping para recurar algo o PostMapping para enviar
    public String findAll(Model model){

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("message", "Este mensaje es un addAtribute");

        return "customer-list"; // devolver el nombre del archivo html
    }
}
