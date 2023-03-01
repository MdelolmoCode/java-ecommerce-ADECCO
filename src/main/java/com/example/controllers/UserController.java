package com.example.controllers;

import com.example.entities.Customer;
import com.example.entities.UserEntity;
import com.example.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
        UserEntity userEntity = new UserEntity();
        userEntity.setCustomer(new Customer());
        model.addAttribute("user", userEntity);
        return "user/sign-in";
    }

    @PostMapping("user/sign-in")
    public String saveSignInForm(@ModelAttribute UserEntity user, Model model) {

        if (!StringUtils.hasLength(user.getUsername()))
            return sendToSignInFormWithErrorMessage(model, "Nombre de usuario vacío.");

        if (!StringUtils.hasLength(user.getPassword()))
            return sendToSignInFormWithErrorMessage(model, "Password vacío.");

        if (!StringUtils.hasLength(user.getEmail()))
            return sendToSignInFormWithErrorMessage(model, "Email vacío.");

        if (userService.existsByUsername(user.getUsername()))
            return sendToSignInFormWithErrorMessage(model, "Nombre de usuario no disponible.");

        if (userService.existsByEmail(user.getEmail()))
            return sendToSignInFormWithErrorMessage(model, "Email no disponible.");

        user.encodePassword(passwordEncoder);

        try {
            userService.save(user);
            return "redirect:/login";
        } catch (Exception e) {
            return sendToSignInFormWithErrorMessage(model, "Error guardando usuario.");
        }
    }

    private String sendToSignInFormWithErrorMessage(Model model, String errorMessage) {
        model.addAttribute("error", errorMessage);
        return getSignInForm(model);
    }
}
