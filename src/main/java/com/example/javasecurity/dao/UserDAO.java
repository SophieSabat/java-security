package com.example.javasecurity.dao;

import com.example.javasecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findUserByName(String name);
}
