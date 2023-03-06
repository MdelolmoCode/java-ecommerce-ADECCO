package com.example.controllers;

import com.example.entities.CartItem;
import com.example.entities.Customer;
import com.example.entities.ShoppingCart;
import com.example.services.CartItemService;
import com.example.services.CustomerService;
import com.example.services.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final CustomerService customerService;
    private final CartItemService cartItemService;

    @GetMapping("/shoppingCarts")
    public String findAll(Model model) {

        List<ShoppingCart> shoppingCarts = shoppingCartService.findAll();
        model.addAttribute("shoppingcarts", shoppingCarts);

        return "shoppingCart/shoppingcarts-list"; //
    }

    @GetMapping("/shoppingCarts/{id}")
    public String findById(Model model, @PathVariable Long id) {

        Optional<Customer> optionalCustomer = customerService.findById(id);

        List<CartItem> cartItems = null;
        Long totalItems = null;
        double totalCost = 0.0;
        if (optionalCustomer.isPresent()) {

            Customer customer = optionalCustomer.get();
            cartItems = shoppingCartService.findByCustomer(customer).get().getCartItems();
            totalCost = shoppingCartService.calculateShoppingCartPrice(shoppingCartService.findById(id).get());
            totalItems = 0L;

            for (CartItem c : cartItems) {
                totalItems = c.getAmount() + totalItems;
            }
        }

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("customer", optionalCustomer);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalCoste", totalCost);

        return "shoppingCart/shoppingcarts-detail";
    }

}
