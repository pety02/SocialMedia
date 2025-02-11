package com.example.socialmedia.controller;

import com.example.socialmedia.dto.LoginUserDTO;
import com.example.socialmedia.dto.RegisterUserDTO;
import com.example.socialmedia.dto.UserDTO;
import com.example.socialmedia.service.interfaces.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.validation.BindingResult.MODEL_KEY_PREFIX;

@Controller
@Slf4j
public class AuthController {
    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute("registeredUser") RegisterUserDTO user,
            BindingResult binding,
            RedirectAttributes redirectAttributes,
            HttpSession httpSession) {

        if (httpSession.getAttribute("loggedInUser") != null) {
            httpSession.invalidate();
        }

        if (binding.hasErrors()) {
            redirectAttributes.addFlashAttribute("registeredUser", binding);
            redirectAttributes.addFlashAttribute(MODEL_KEY_PREFIX + "registeredUser", user);
            return "redirect:register";
        }

        LocalDate today = LocalDate.now();
        LocalDate validMinDateForRegistration = today.minusYears(18);

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("errors", "Passwords do not match.");
            redirectAttributes.addFlashAttribute("registeredUser", user);
            return "redirect:/register";
        }

        if (user.getDateOfBirth() == null || user.getDateOfBirth().isAfter(validMinDateForRegistration)) {
            redirectAttributes.addFlashAttribute("errors", "You must be at least 18 years old to register.");
            redirectAttributes.addFlashAttribute("registeredUser", user);
            return "redirect:/register";
        }

        try {
            Optional<RegisterUserDTO> registered = userService.register(user);
            if (registered.isEmpty()) {
                redirectAttributes.addFlashAttribute("errors", "Registration failed. Please try again.");
                redirectAttributes.addFlashAttribute("registeredUser", user);
                return "redirect:/register";
            }
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errors", "An unexpected error occurred. Please try again.");
            redirectAttributes.addFlashAttribute("registeredUser", user);
            return "redirect:/register";
        }
    }

    @PostMapping("/login")
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

            return "redirect:/home";
        } catch (Exception e) {
            if (!model.containsAttribute("loggedInUser")) {
                model.addAttribute("loggedInUser", user != null ? user : new LoginUserDTO());
            }
            return "redirect:login";
        }
    }
}