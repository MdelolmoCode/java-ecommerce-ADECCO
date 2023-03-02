package com.example.controllers;

import com.example.entities.CartItem;
import com.example.services.CartItemService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class CartItemController
{
    private final CartItemService cartItemService;

    @GetMapping("cartitems")
    public String findAll(Model model)
    {
        List<CartItem> cartItemList = cartItemService.findAllByOrderByShoppingCartId();
        model.addAttribute("cartItems", cartItemList);
        return "cartItem/cartitem-list";
    }
}
