package com.example.javasecurity.controllers;


import com.example.javasecurity.dao.UserDAO;
import com.example.javasecurity.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String home() {
        return "home";
    }


    @GetMapping("/admin/test")
    public String  adminTest() {
        return "admin test";
    }

    @GetMapping("/user/test")
    public String  userTest() {
        return "user test";
    }

    @GetMapping("/wide")
    public String wide() {
        return "wide url";
    }


    @PostMapping("/register")
    public void register(@RequestBody User user) {
        String encode = passwordEncoder.encode(user.getPass());
        user.setPass(encode);
        System.out.println(user);
        userDAO.save(user);
    }
}
