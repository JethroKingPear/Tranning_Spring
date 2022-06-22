package com.example.demo.util;

import java.util.Date;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.example.demo.Models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTTokenProvider {

    private static final String JWT_SECRET = "this is secret";
    private static final String AUTH_HEADER_PARAM_NAME = "Authorization";
    private static final String AUTH_HEADER_TOKEN_PREFIX = "Bearer";
    private final String AUTH_HEADER_USERNAME = "username";
    private static final long JWT_EXPIRATION = 60480000L;

    // get token from http request header
    public String getToken(HttpServletRequest request) {
        String authToken = request.getHeader(AUTH_HEADER_PARAM_NAME);
        if (Objects.isNull(authToken)) {
            return null;
        }
        return authToken.substring(AUTH_HEADER_TOKEN_PREFIX.length());
    }

    public String generationToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + JWT_EXPIRATION);
        Claims claims = Jwts.claims();
        claims.put(AUTH_HEADER_USERNAME, username);
        String token = Jwts.builder().setClaims(claims).setIssuedAt(new Date()).setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET.getBytes()).compact();
        return token;
    }

    public String getUserIdFromJWT(String token) throws Exception {
        String username = null;

        try {
            final Claims claims = Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
            username = String.valueOf(claims.get(AUTH_HEADER_USERNAME));
        } catch (Exception e) {
            throw new Exception("INVALID JWT TOKEN");
        }
        return username;
    }

    public boolean validateToken(String authToken) {
        try {
            boolean isValid = true;
            try {
                final Claims claims = Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(authToken)
                        .getBody();
                isValid = !(claims.getExpiration().before(new Date()));
            } catch (Exception e) {
                throw new Exception("INVALID JWT TOKEN");
            }

            return isValid;
        } catch (Exception e) {
            // TODO: handle exception
            log.error("validateToken : ", e);

        }
        return false;
    }
}
