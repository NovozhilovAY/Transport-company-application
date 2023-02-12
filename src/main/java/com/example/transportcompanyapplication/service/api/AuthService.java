package com.example.transportcompanyapplication.service.api;

import com.example.transportcompanyapplication.dto.AuthRequest;
import com.example.transportcompanyapplication.dto.AuthResponse;
import com.example.transportcompanyapplication.dto.RefreshRequest;
import com.example.transportcompanyapplication.dto.RefreshResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<AuthResponse> login(AuthRequest request);
    ResponseEntity<RefreshResponse> refresh(RefreshRequest request);
}
