package com.ecommerce.service;

import com.ecommerce.dto.LoginRequest;
import com.ecommerce.dto.LoginResponse;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class AuthService {

    // Dummy credentials for demo purposes
    private static final String DEMO_USERNAME = "admin";
    private static final String DEMO_PASSWORD = "password";

    public LoginResponse login(LoginRequest request) {
        if (request == null || request.getUsername() == null || request.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required");
        }

        boolean valid = DEMO_USERNAME.equals(request.getUsername())
                && DEMO_PASSWORD.equals(request.getPassword());

        if (!valid) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        // Generate a dummy token (NOT secure - demo only)
        String raw = request.getUsername() + ":" + UUID.randomUUID();
        String token = Base64.getEncoder().encodeToString(raw.getBytes());

        return new LoginResponse(token, request.getUsername(), "Login successful");
    }
}
