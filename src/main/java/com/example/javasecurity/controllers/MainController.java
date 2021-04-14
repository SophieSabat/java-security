package com.example.javasecurity.controllers;

import com.example.javasecurity.dao.UserDAO;
import com.example.javasecurity.mailService.MailService;
import com.example.javasecurity.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class MainController {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

    @PostMapping("/save")
    public void save(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @GetMapping("/test")
    public void test() {
        System.out.println("success!!!!!!!!!!");
    }

    @GetMapping("/email/{email}")
    public void sendEmail(@PathVariable String email) {
        System.out.println(email);
        mailService.send(email);
    }

    @GetMapping("/activate/{id}")
    public void activateUser(@PathVariable int id) {
        User user = userDAO.findById(id).get();
        user.setEnabled(true);
        userDAO.save(user);
        System.out.println("succccccceeeeeesssssss!!!!");
    }
}
