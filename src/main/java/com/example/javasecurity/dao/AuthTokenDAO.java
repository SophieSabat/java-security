package com.example.javasecurity.dao;

import com.example.javasecurity.models.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenDAO extends JpaRepository<AuthToken, Integer> {
}
