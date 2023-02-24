package com.example.controllers;

import com.example.entities.Product;
import com.example.repositories.CategoryRepository;
import com.example.repositories.ManufacturerRepository;
import com.example.repositories.ProductRepository;
import com.example.services.CategoryService;
import com.example.services.ManufacturerService;
import com.example.services.ProductService;
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
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductService productService;
    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerService manufacturerService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;



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
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("manufacturer", manufacturerService.findAll());
        return "product/product-form";
    }

    @GetMapping("products/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        Optional<Product> productOpt = productService.findById(id);
        if (productOpt.isPresent()) {
            model.addAttribute("product", productOpt.get());
            model.addAttribute("categories", categoryRepository.findAll());
            model.addAttribute("manufacturer", manufacturerRepository.findAll());
        } else {
            model.addAttribute("error", "Product not found");
        }
        return "product/product-form";
    }

    @PostMapping("products") // POST http://localhost:8080/products
    public String saveForm(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products"; // Redirección a GET /products
    }

    @GetMapping("products/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
