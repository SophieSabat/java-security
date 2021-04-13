package com.example.javasecurity.controllers;

import com.example.javasecurity.dao.UserDAO;
import com.example.javasecurity.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class MainController {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save")
    private void saveUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @GetMapping("/test")
    private void test() {
        System.out.println("success!!!!!!!!");
    }
}
