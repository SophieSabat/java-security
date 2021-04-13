package com.example.javasecurity.configs;


import com.example.javasecurity.dao.AuthTokenDAO;
import com.example.javasecurity.models.AuthToken;
import com.example.javasecurity.models.User;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AllRequestFilter extends GenericFilter {

    private AuthTokenDAO authTokenDAO;

    public AllRequestFilter(AuthTokenDAO authTokenDAO) {
        this.authTokenDAO = authTokenDAO;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = null;

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        String authorizationToken = servletRequest.getHeader("Authorization");
        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
            String token = authorizationToken.replace("Bearer ", "");
            String tokenData = Jwts.parser()
                    .setSigningKey("okten".getBytes())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            AuthToken authToken = authTokenDAO.findByToken(token);
            User user = authToken.getUser();

            if (user != null) {
                authentication = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
