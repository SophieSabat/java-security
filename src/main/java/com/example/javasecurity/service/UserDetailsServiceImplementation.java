package com.example.javasecurity.service;

import com.example.javasecurity.dao.ClientDAO;
import com.example.javasecurity.models.Client;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private ClientDAO clientDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientDAO.findClientByLogin(username);
        return client;
    }
}
