package com.example.core.utility;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    // Use a long enough static secret (min 512 bits for HS512)
    private static final String SECRET = "my-super-secret-key-that-is-long-enough-to-use-for-hs512-token-signing-2024";

    // Shared key for both signing and validation
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    
   
    public String generateToken(UserDetails userDetails) {
    	System.out.println("Key used for signing: " + Base64.getEncoder().encodeToString(key.getEncoded()));

        return Jwts.builder()
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiry
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }
}
