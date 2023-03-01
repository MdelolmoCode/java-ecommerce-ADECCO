package com.example.controllers;

import com.example.entities.CartItem;
import com.example.entities.Customer;
import com.example.entities.ShoppingCart;
import com.example.repositories.CustomerRepository;
import com.example.services.CartItemService;
import com.example.services.CustomerService;
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
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartService.findById(id);

        if(optionalShoppingCart.isPresent()){

            List<CartItem> cartItems = optionalShoppingCart.get().getCartItems();

            if(!cartItems.isEmpty()){
                for (CartItem c: cartItems){
                    cartItemService.deleteById(c.getId());
                }
            }
            shoppingCartService.deleteById(optionalShoppingCart.get().getId());
        }
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

