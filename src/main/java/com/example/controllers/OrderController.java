package com.example.controllers;

import com.example.entities.Order;
import com.example.entities.ShoppingCart;
import com.example.services.AddressService;
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
public class OrderController {

    private final OrderService orderService;
    private final AddressService addressService;
    private final ShoppingCartService shoppingCartService;

    @GetMapping("orders")
    public String findAll(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order/order-list";
    }

    @GetMapping("orders/{id}")
    public String findById(Model model, @PathVariable Long id) {
        Optional<Order> orderOpt = orderService.findById(id);
        if (orderOpt.isPresent())
            model.addAttribute("order", orderOpt.get());
        else
            model.addAttribute("error", "No existe ese pedido.");

        return "order/order-detail";
    }

    @GetMapping("orders/create/{cartId}")
    public String createForm(Model model, @PathVariable Long cartId) {
        Optional<ShoppingCart> shoppingCartOpt = shoppingCartService.findById(cartId);
        if (shoppingCartOpt.isPresent()) {
            ShoppingCart shoppingCart = shoppingCartOpt.get();
            Order order = new Order();
            order.setCustomer(shoppingCart.getCustomer());
            order.setCartItems(shoppingCart.getCartItems());
            order.setOrderNumber(orderService.getAvailableOrderNumber());

            model.addAttribute("order", order);
            model.addAttribute("cartId", cartId);
            model.addAttribute("addresses", addressService.findAll());
        }
        else
            model.addAttribute("error", "No existe ese carrito.");

        return "order/order-form";
    }

    @GetMapping("orders/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        Optional<Order> orderOpt = orderService.findById(id);
        if (orderOpt.isPresent()) {
            model.addAttribute("order", orderOpt.get());
            model.addAttribute("addresses", addressService.findAll());
        } else {
            model.addAttribute("error", "No existe se pedido.");
        }

        return "order/order-form";
    }

    @PostMapping("orders/{cartId}")
    public String saveForm(@ModelAttribute Order order, @PathVariable Long cartId) {
        Optional<ShoppingCart> shoppingCartOpt = shoppingCartService.findById(cartId);
        shoppingCartOpt.ifPresent(orderService::emptyCart);
        orderService.save(order);

        if (shoppingCartOpt.isPresent())
            return "redirect:/customers/" + shoppingCartOpt.get().getCustomer().getId() + "/orders";
        else
            return "redirect:/orders";
    }

    @GetMapping("orders/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }

}
