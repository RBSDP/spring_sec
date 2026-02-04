package com.example.spring_sec.service;

import java.util.Map;

public class JwtService {

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 3)) // 10 hours
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode("your-secret-key-base64-encoded");
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String extractUserName(String token) {
        return "";
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return true;
    }
}