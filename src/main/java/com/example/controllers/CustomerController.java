package com.example.controllers;

import com.example.entities.Customer;
import com.example.repositories.CustomerRepository;
import com.example.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller

public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    // Crear metodos y cada metodo irá mapeado a una URL http
    // Normalmente estos metodos devuelven String; que es el nombre del archivo html

    @GetMapping("/customers") // Puede ser getmapping para recurar algo o PostMapping para enviar
    public String findAll(Model model){

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("message", "Este mensaje es un addAtribute");

        return "customer/customer-list"; // devolver el nombre del archivo html
    }

    @GetMapping("/customers/{id}")
    public String findById(Model model, @PathVariable Long id){
        Optional<Customer> customerOptional = customerService.findById(id);
        if(customerOptional.isPresent()){

            model.addAttribute("customer", customerOptional.get());
        }else{
            model.addAttribute("error", "customer not found");
        }
        return "customer/customer-detail";
    }

    @GetMapping("/customers/{id}/borrar")
    public String deleteById(@PathVariable Long id) {
        customerRepository.deleteById(id);

        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}/editar")
    public String editForm(Model model, @PathVariable Long id){
        Optional<Customer> optionalCustomer = customerService.findById(id);

        if(optionalCustomer.isPresent()){
            model.addAttribute("customer", optionalCustomer.get());
        }


        return "customer/customer-form";
    }

}

