package com.example.controllers;

import com.example.entities.Category;
import com.example.entities.Product;
import com.example.repositories.CategoryRepository;
import com.example.services.CategoryService;
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
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    // http://localhost:8080/categories
    @GetMapping("categories")
    public String findAll(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/category-list";
    }

    @GetMapping("categories/create")
    public String createForm(Model model) {
        model.addAttribute("category", new Category()); // objeto vacío para rellenar desde el formulario
        return "category/category-form";
    }

    @GetMapping("categories/{id}/edit")
    public String editForm(Model model, @PathVariable Long id) {
        Optional<Category> categoryOpt = categoryService.findById(id);
        if (categoryOpt.isPresent()) {
            model.addAttribute("category", categoryOpt.get());
        } else {
            model.addAttribute("error", "Category not found");
        }
        return "category/category-form";
    }

    @PostMapping("categories") // POST http://localhost:8080/categories
    public String saveForm(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories"; // Redirección a GET /categories
    }

    @GetMapping("categories/{id}/delete")
    public String deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}
