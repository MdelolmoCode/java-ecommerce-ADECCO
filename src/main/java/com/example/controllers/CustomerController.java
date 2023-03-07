package com.example.controllers;

import com.example.entities.CartItem;
import com.example.entities.Customer;
import com.example.entities.Order;
import com.example.entities.ShoppingCart;
import com.example.repositories.CustomerRepository;
import com.example.services.CartItemService;
import com.example.services.CustomerService;
import com.example.services.OrderService;
import com.example.services.ShoppingCartService;
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

public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;
    private final ShoppingCartService shoppingCartService;
    private final CartItemService cartItemService;
    private final OrderService orderService;


    @GetMapping("/customers")
    public String findAll(Model model){

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);

        return "customer/customer-list";
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

        customerService.deleteById(id);

        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}/editar")
    public String editForm(Model model, @PathVariable Long id){
        Optional<Customer> optionalCustomer = customerService.findById(id);

        if(optionalCustomer.isPresent()){
            model.addAttribute("customer", optionalCustomer.get());
        }
        else {
        model.addAttribute("error", "Product not found");
    }
        return "customer/customer-form";
    }

    @GetMapping("/customers/newForm")
    public String newForm(Model model){

        model.addAttribute("customer", new Customer());

        return "customer/customer-form";
    }

    @PostMapping("/customers")
    public String save(@ModelAttribute Customer customer){
            customerService.save(customer);
        return "redirect:/customers";
    }
}

