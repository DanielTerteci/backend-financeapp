package com.finance.app.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final String COMMON_SECRET_KEY = "oA9FhWkZp3s6v9y$B&E)H@McQfTjWnZr";

    public String generateToken(String userId, String username) {
        Key secretKey = generateSecretKeyForUser(userId);
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 15 * 60 * 1000); // 15 minute în milisecunde

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateSecretKeyForUser(String userId) {
        return Keys.hmacShaKeyFor(COMMON_SECRET_KEY.getBytes());
    }
}