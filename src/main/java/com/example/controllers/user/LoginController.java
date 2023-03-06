package com.example.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("login")
    public String login() {
        return "user/login";
    }

    @GetMapping("logout")
    public String logout() {
        return "user/logout";
    }

}
