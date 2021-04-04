package com.example.javasecurity.dao;

import com.example.javasecurity.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Integer> {
    Client findClientByLogin(String login);
}
