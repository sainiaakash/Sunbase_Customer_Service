package com.example.sunbase_customerapp.controller;

import com.example.sunbase_customerapp.model.AuthRequest;
import com.example.sunbase_customerapp.model.AuthResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/auth")
public class AuthController {

    private final String secretKey = generateRandomKey(32);

    @PostMapping("/login")
    private ResponseEntity<?> authenticateUser(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        boolean isAuthenticated = authenticate(username, password);

        if (isAuthenticated) {
            String token = generateToken(username);
            // Return the token in the response
            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }

    private boolean authenticate(String username, String password) {
        return "test@sunbasedata.com".equals(username) && "Test@123".equals(password);
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private String generateRandomKey(int keySizeBytes) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[keySizeBytes];
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

}
