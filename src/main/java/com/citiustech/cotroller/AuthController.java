package com.citiustech.cotroller;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.citiustech.model.User;
import com.citiustech.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        user.setRoles(Set.of("USER")); // Default role for new users
        userService.register(user);
        model.addAttribute("message", "Registration successful! Please log in.");
        return "register";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        return userService.login(username, password).map(user -> {
            if (user.getRoles().contains("PATIENT")) {
                return "redirect:/patient/dashboard";
            }
            model.addAttribute("message", "Login successful!");
            return "login";
        }).orElseGet(() -> {
            model.addAttribute("error", "Invalid credentials!");
            return "login";
        });
    }
}
