package com.example.controllers;

import com.example.entities.UserEntity;
import com.example.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("user/sign-in")
    public String getSignInForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "user/sign-in";
    }

    @PostMapping("user/sign-in")
    public String saveSignInForm(@ModelAttribute UserEntity user, Model model) {

        if (userService.existsByUsername(user.getUsername())) {
            model.addAttribute("error", "Nombre de usuario no disponible.");
            return getSignInForm(model);
        }

        if (userService.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "e-mail no disponible.");
            return getSignInForm(model);
        }

        user.encodePassword(passwordEncoder);

        try {
            userService.save(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Error guardando usuario.");
            return getSignInForm(model);
        }
    }
}
