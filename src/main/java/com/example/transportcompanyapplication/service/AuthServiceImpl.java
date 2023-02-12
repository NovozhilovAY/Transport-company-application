package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.AuthRequest;
import com.example.transportcompanyapplication.dto.AuthResponse;
import com.example.transportcompanyapplication.dto.RefreshRequest;
import com.example.transportcompanyapplication.dto.RefreshResponse;
import com.example.transportcompanyapplication.service.api.AuthService;
import com.example.transportcompanyapplication.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<AuthResponse> login(AuthRequest request){
        UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails user = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);
        List<String> roles = this.getListOfAuthorities(user);
        AuthResponse response = new AuthResponse(user.getUsername(), jwt, refreshToken, roles);
        return new  ResponseEntity<>(response, HttpStatus.OK);
    }

    private List<String> getListOfAuthorities(UserDetails userDetails){
        List<String> listOfAuthorities = new ArrayList<>();
        userDetails.getAuthorities().forEach(
                grantedAuthority -> listOfAuthorities.add(grantedAuthority.toString())
        );
        return listOfAuthorities;
    }

    @Override
    public ResponseEntity<RefreshResponse> refresh(RefreshRequest request) {
        if(request.getRefreshToken() == null || !jwtUtils.validateJwtToken(request.getRefreshToken())){
            throw new AccountExpiredException("Invalid refreshToken");
        }
        String username = jwtUtils.getUserNameFromToken(request.getRefreshToken());
        UserDetails user = userDetailsService.loadUserByUsername(username);
        String newJwt = jwtUtils.generateJwtToken(user);
        return new ResponseEntity<>(new RefreshResponse(newJwt), HttpStatus.OK);
    }
}
