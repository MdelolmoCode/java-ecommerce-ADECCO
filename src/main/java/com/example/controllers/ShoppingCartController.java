package com.example.controllers;

import com.example.entities.ShoppingCart;
import com.example.services.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @GetMapping("/shoppingCarts")
    public String findAll(Model model){

        List<ShoppingCart> shoppingCarts = shoppingCartService.findAll();
        model.addAttribute("shoppingcarts", shoppingCarts);
        model.addAttribute("message", "Este mensaje es un addAtribute");

        return "shoppingCart/shoppingcarts-list"; //
    }
}
