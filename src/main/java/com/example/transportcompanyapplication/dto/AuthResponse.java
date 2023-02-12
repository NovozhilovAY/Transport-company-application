package com.example.transportcompanyapplication.dto;

import java.util.List;

public class AuthResponse {
    private String username;
    private String token;
    private String refreshToken;
    private List<String> roles;

    public AuthResponse() {
    }

    public AuthResponse(String username, String token, String refreshToken, List<String> roles) {
        this.username = username;
        this.token = token;
        this.refreshToken = refreshToken;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
