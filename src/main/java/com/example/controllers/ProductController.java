package com.example.controllers;

import com.example.entities.Product;
import com.example.repositories.ManufacturerRepository;
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
    private final ManufacturerRepository manufacturerRepository;

    // http://localhost:8080/products
    @GetMapping("products")
    public String findAll(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/product-list";
    }

    @GetMapping("products/{id}")
    public String findById(Model model, @PathVariable Long id){
        Optional<Product> productOpt = productService.findByIdWithCategories(id);
        if(productOpt.isPresent())
            model.addAttribute("product", productOpt.get());
        else
            model.addAttribute("error","Product not found");
        return "product/product-detail";
    }

    @GetMapping("products/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product()); // objeto vacío para rellenar desde el formulario
        model.addAttribute("manufacturer", manufacturerRepository.findAll());
        return "product/product-form";
    }

}
