
package com.example.javasecurity.configs;


import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class RequestProcessingJWTFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("do filter");

        Authentication authentication = null;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        System.out.println(token);

        if (token != null && token.startsWith("Bearer")) {
            String clearToken = token.replace("Bearer ", "");
            // розпарсили
            String tokenData = Jwts.parser()
                    .setSigningKey("yes".getBytes())
                    .parseClaimsJws(clearToken)
                    .getBody()
                    .getSubject();
            System.out.println(tokenData);

            if (tokenData.equals("asd"))
                authentication = new UsernamePasswordAuthenticationToken(tokenData, "asd");

            if (tokenData.equals("qwe"))
                authentication = new UsernamePasswordAuthenticationToken(tokenData, "qwe");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);

    }
}
