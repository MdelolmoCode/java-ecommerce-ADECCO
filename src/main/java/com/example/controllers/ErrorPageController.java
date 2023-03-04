package com.example.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (statusCode != null) {
            if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                model.addAttribute("message", "401 - Unauthorized");
            }
            else if(statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("message", "404 - Resource not found");
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("message", "500 - Internal server error");
            }
            else {
                model.addAttribute("message", statusCode + " - Error");
            }
        }
        return "error";
    }
}
