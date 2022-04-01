package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.dto.AuthRequest;
import com.example.transportcompanyapplication.dto.AuthResponse;
import com.example.transportcompanyapplication.dto.RefreshRequest;
import com.example.transportcompanyapplication.dto.RefreshResponse;
import com.example.transportcompanyapplication.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(value = "*")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refresh(@RequestBody RefreshRequest request){
        return authService.refresh(request);
    }
}
