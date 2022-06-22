package com.example.demo.Controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.JWTTokenProvider;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwts;

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestParam("userId") int userId, @RequestParam("password") String password) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userId + "", password));
        if (authenticate.isAuthenticated()) {
            User user = (User) authenticate.getPrincipal();
            String token = jwts.generationToken(user.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return new ResponseEntity<>("INVALID USERNAME/PASSWORD", HttpStatus.BAD_REQUEST);
        }
    }

}
