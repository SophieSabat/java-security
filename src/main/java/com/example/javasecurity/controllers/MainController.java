package com.example.javasecurity.controllers;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    @GetMapping("/")
    public String home(Principal principal) {

        System.out.println(principal);
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        SecurityContextHolder
                .getContext()
                .setAuthentication(
                        new UsernamePasswordAuthenticationToken("qwerty","qwerty")
                );

        return "home page";
    }

    @GetMapping("/user/test")
    public String userTest() {
        return "user test";
    }

    @GetMapping("/admin/test")
    public String adminTest() {
        return "admin test";
    }

    @GetMapping("/wide")
    public String wide() {
        return "wide url";
    }
}
