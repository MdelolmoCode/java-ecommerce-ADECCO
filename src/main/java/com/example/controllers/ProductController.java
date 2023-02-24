package com.example.controllers;

import com.example.entities.Product;
import com.example.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;

    // http://localhost:8080/products
    @GetMapping("products")
    public String findAll(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("message", "Esto es un mensaje con el metodo addAttribute");
        return "product-list";
    }

    @GetMapping("foods/{id}")
    public String findById(Model model, @PathVariable Long id){
        Optional<Product> productOpt = productService.findById(id);
        if(productOpt.isPresent())
            model.addAttribute("products", productOpt.get());
        else
            model.addAttribute("error","Product not found");
        return "product-detail";
    }

}
