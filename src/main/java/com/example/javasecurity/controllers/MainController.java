package com.example.javasecurity.controllers;

import com.example.javasecurity.dao.ClientDAO;
import com.example.javasecurity.models.Client;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
public class MainController {

    private ClientDAO clientDAO;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signUp")
    public void signUp(@RequestBody Client client) {
        String pass = client.getPass();
        String encode = passwordEncoder.encode(pass);
        client.setPass(encode);
        clientDAO.save(client);
    }

    @GetMapping("/")
    public List<Client> home() {
        return clientDAO.findAll();
    }
}
