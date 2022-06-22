package com.example.demo.configuration;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Services.UserService;
import com.example.demo.util.JWTTokenProvider;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JWTTokenProvider jwt;

    @Autowired
    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = jwt.getToken(request);
        if (!Objects.isNull(token)) {
            try {
                String username = jwt.getUserIdFromJWT(token);
                if (!Objects.isNull(username) && jwt.validateToken(token)) {
                    UserDetails userDetails = userService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(), null, userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    throw new Exception("INVALID USERNAME");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpStatus.BAD_REQUEST.value());
            }
        }

        filterChain.doFilter(request, response);

    }

}
