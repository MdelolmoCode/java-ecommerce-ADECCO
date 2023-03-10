package com.example.controllers;

import com.example.entities.Product;
import com.example.entities.UserEntity;
import com.example.repositories.CategoryRepository;
import com.example.repositories.ManufacturerRepository;
import com.example.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class ProductController {

    private final ProductService productService;
    private final ManufacturerRepository manufacturerRepository;
    private final ManufacturerService manufacturerService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final FileService fileService;

    @GetMapping("/")
    public String index() {
        return "redirect:/products";
    }

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
    public String saveForm(Model model,
                           @ModelAttribute Product product,
                           @RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            productService.save(product);
            return "redirect:/products";
        }

        try {
            String fileName = fileService.storeInFileSystem(file);
            product.setImageUrl(fileName);
            productService.save(product);
            return "redirect:/products";
        } catch (Exception e) {
            model.addAttribute("error", "No se ha podido guardar la imagen");
            return findAll(model);
        }
    }

    @GetMapping("products/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("products/{id}/add")
    public String addProductToShoppingCart(Model model, @PathVariable Long id){
        UserEntity user = userService.getCurrentUser();
        if (user == null)
            return "redirect:/products";

        productService.addProductToShoppingCart(id);
        Long customerId = user.getCustomer().getId();
        return "redirect:/shoppingCarts/byCustomer/" + customerId;
    }
}
