package com.example.socialmedia.controller;

import com.example.socialmedia.dto.LoginUserDTO;
import com.example.socialmedia.dto.RegisterUserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/register")
    public String getRegisterForm(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("loggedInUser") != null) {
            httpSession.invalidate();
        }
        if (!model.containsAttribute("loggedInUser")) {
            model.addAttribute("loggedInUser", new RegisterUserDTO());
        }
        return "register";
    }

    @GetMapping("/")
    public String getLoginForm(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("loggedInUser") != null) {
            httpSession.invalidate();
        }
        if (!model.containsAttribute("loggedInUser")) {
            model.addAttribute("loggedInUser", new LoginUserDTO());
        }
        return "login";
    }
}