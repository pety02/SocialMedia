package com.example.socialmedia.controller;

import com.example.socialmedia.dto.LoginUserDTO;
import com.example.socialmedia.dto.RegisterUserDTO;
import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@RestController
@Slf4j
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterUserDTO user,
                           final BindingResult binding,
                           RedirectAttributes redirectAttributes,
                           HttpSession httpSession) {
        if (httpSession.getAttribute("loggedInUser") != null) {
            httpSession.invalidate();
        }
        if (binding.hasErrors()) {
            log.error("Error registering user: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("registeredUser", user);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "registeredUser", binding);
            return "redirect:register";
        }
        try {
            LocalDate today = LocalDate.now();
            int todayDay = today.getDayOfMonth();
            int todayMonth = today.getMonthValue();
            int todayYear = today.getYear();
            LocalDate validMinDateForRegistration = LocalDate.of(todayYear - 18, todayMonth, todayDay);
            if(user.getDateOfBirth().isAfter(validMinDateForRegistration)) {
                String errors = "Sorry, but You are too young to register in this site.";
                redirectAttributes.addFlashAttribute("errors", errors);

                if (!redirectAttributes.containsAttribute("registeredUser")) {
                    redirectAttributes.addFlashAttribute("registeredUser", user);
                }
                return "redirect:register";
            }

            RegisterUserDTO registeredUser = userService.register(user).orElse(null);
            if (registeredUser == null) {
                String errors = "Invalid user registration data.";
                redirectAttributes.addFlashAttribute("errors", errors);

                if (!redirectAttributes.containsAttribute("registeredUser")) {
                    redirectAttributes.addFlashAttribute("registeredUser", user);
                }
                return "redirect:register";
            }

            if (!redirectAttributes.containsAttribute("registeredUser")) {
                redirectAttributes.addFlashAttribute("registeredUser", user);
            }
            return "redirect:login";
        } catch (Exception e) {
            if (!redirectAttributes.containsAttribute("registeredUser")) {
                redirectAttributes.addFlashAttribute("registeredUser", user);
            }
            return "redirect:register";
        }
    }

    @PostMapping("/")
    public String login(@Valid @ModelAttribute LoginUserDTO user,
                        final BindingResult binding,
                        Model model,
                        RedirectAttributes redirectAttributes,
                        HttpSession httpSession) {
        if (httpSession.getAttribute("loggedInUser") != null) {
            httpSession.invalidate();
        }
        if (binding.hasErrors()) {
            log.error("Error logging user in: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("loggedInUser", user);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "loggedInUser", binding);
            return "redirect:login";
        }
        try {
            UserDTO loggedInUser = userService.login(user).orElse(null);
            if (loggedInUser == null) {
                String errors = "Invalid user credentials.";
                redirectAttributes.addAttribute("errors", errors);
                return "redirect:login";
            }

            httpSession.setAttribute("loggedInUser", loggedInUser);

            return "redirect:home";
        } catch (Exception e) {
            if (!model.containsAttribute("loggedInUser")) {
                model.addAttribute("loggedInUser", user != null ? user : new LoginUserDTO());
            }
            return "redirect:login";
        }
    }
}