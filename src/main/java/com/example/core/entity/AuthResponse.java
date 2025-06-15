package com.example.core.entity;

public class AuthResponse {
    private String token;

    // All-args constructor
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }

    // Setter
    public void setToken(String token) {
        this.token = token;
    }

    // toString()
    @Override
    public String toString() {
        return "AuthResponse(token=" + this.getToken() + ")";
    }

}
