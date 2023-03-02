package com.example.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class AppController {

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

}
