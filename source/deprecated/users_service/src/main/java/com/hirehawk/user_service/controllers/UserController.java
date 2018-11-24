package com.hirehawk.user_service.controllers;

import com.hirehawk.user_service.dao.UserRepository;
import com.hirehawk.user_service.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.hirehawk.user_service.validators.UserValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;

    @PostMapping("/login")
    public String login() {
        //ADD SOME MAGIC WITH KEYCLOAK
        return "";
    }

    @PostMapping("/register")
    public String register(Model model, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
        userValidator.validate(user, bindingResult);

        if (!bindingResult.hasErrors()) {
            //ADD SOME MAGIC WITH KEYCLOAK
            userRepository.save(user);
        }
        return "index";
    }

    @GetMapping(value = "/user/{id}", produces = "application/json")
    public User getUser(@PathVariable("id") Integer id) {
        User user = userRepository.findById(id);
        user.setPassword("");
        user.setConfirmationPassword("");
        return user;
    }

    @PostMapping(value = "/user/{id}", produces = "application/json")
    public String updateUser(Model model, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
        userRepository.save(user);
        return "success";
    }
}



