package com.example.controllers;

import com.example.entities.CartItem;
import com.example.services.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class CartItemController
{
    private final CartItemService cartItemService;

    @GetMapping("cartitems")
    public String findAll(Model model)
    {

        model.addAttribute("cartItems", cartItemService.findAll());
        return "cartitem/cartitem-list";
    }

    @GetMapping("cartitem/{id}")
    public String findById(Model model, @PathVariable Long id)
    {
        Optional<CartItem> cartItemOpt = cartItemService.findById(id);

        if(cartItemOpt.isEmpty())
            model.addAttribute("error", "No existe el cartItem solicitado");
        else
            model.addAttribute("cartitem", cartItemOpt.get());

        return "cartitem/cartitem-detail";
    }

}
