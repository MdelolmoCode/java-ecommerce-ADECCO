package com.example.controllers;

import com.example.entities.Order;
import com.example.services.OrderService;
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

    @GetMapping("orders")
    public String findAll(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "order-list";
    }

    @GetMapping("orders/{id}")
    public String findById(Model model, @PathVariable Long id) {
        Optional<Order> orderOpt = orderService.findById(id);
        if (orderOpt.isPresent())
            model.addAttribute("order", orderOpt.get());
        else
            model.addAttribute("error", "Order not found");

        return "order-detail";
    }

    @GetMapping("orders/create")
    public String createForm(Model model) {
        model.addAttribute("order", new Order());
        return "order-form";
    }

    @GetMapping("orders/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        Optional<Order> orderOpt = orderService.findById(id);
        if (orderOpt.isPresent()) {
            model.addAttribute("order", orderOpt.get());
        } else {
            model.addAttribute("error", "Order not found");
        }

        return "order-form";
    }

    @PostMapping("orders")
    public String saveForm(@ModelAttribute Order order) {
        orderService.save(order);
        return "redirect:/orders";
    }

    @GetMapping("orders/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }

}
