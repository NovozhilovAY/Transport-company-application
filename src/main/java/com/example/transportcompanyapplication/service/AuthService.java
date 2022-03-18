package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.AuthRequest;
import com.example.transportcompanyapplication.dto.AuthResponse;
import com.example.transportcompanyapplication.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager,JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<AuthResponse> login(AuthRequest request){
        UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        List<String> roles = this.getListOfAuthorities(user);
        AuthResponse response = new AuthResponse(user.getUsername(), jwt, roles);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    private List<String> getListOfAuthorities(UserDetails userDetails){
        List<String> listOfAuthorities = new ArrayList<>();
        userDetails.getAuthorities().forEach(
                grantedAuthority -> listOfAuthorities.add(grantedAuthority.toString())
        );
        return listOfAuthorities;
    }
}
