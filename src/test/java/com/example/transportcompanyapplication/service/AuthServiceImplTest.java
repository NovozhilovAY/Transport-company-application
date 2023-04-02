package com.example.transportcompanyapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.dto.AuthRequest;
import com.example.transportcompanyapplication.dto.AuthResponse;
import com.example.transportcompanyapplication.dto.RefreshRequest;
import com.example.transportcompanyapplication.dto.RefreshResponse;
import com.example.transportcompanyapplication.security.SecurityUser;
import com.example.transportcompanyapplication.util.JwtUtils;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthServiceImplTest {
    @Autowired
    private AuthServiceImpl authServiceImpl;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private UserDetailsService userDetailsService;

    /**
     * Method under test: {@link AuthServiceImpl#login(AuthRequest)}
     */
    @Test
    void testLogin() throws AuthenticationException {
        when(jwtUtils.generateJwtToken((UserDetails) any())).thenReturn("ABC123");
        when(jwtUtils.generateRefreshToken((UserDetails) any())).thenReturn("ABC123");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken(new User("janedoe", "iloveyou", new ArrayList<>()), "Credentials"));
        ResponseEntity<AuthResponse> actualLoginResult = authServiceImpl.login(new AuthRequest("janedoe", "iloveyou"));
        assertTrue(actualLoginResult.hasBody());
        assertTrue(actualLoginResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualLoginResult.getStatusCode());
        AuthResponse body = actualLoginResult.getBody();
        assertEquals("ABC123", body.getRefreshToken());
        assertEquals("janedoe", body.getUsername());
        assertTrue(body.getRoles().isEmpty());
        assertEquals("ABC123", body.getToken());
        verify(jwtUtils).generateJwtToken((UserDetails) any());
        verify(jwtUtils).generateRefreshToken((UserDetails) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#login(AuthRequest)}
     */
    @Test
    void testLogin2() throws AuthenticationException {
        when(jwtUtils.generateJwtToken((UserDetails) any())).thenReturn("ABC123");
        when(jwtUtils.generateRefreshToken((UserDetails) any())).thenReturn("ABC123");

        ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("Role"));
        TestingAuthenticationToken testingAuthenticationToken = new TestingAuthenticationToken(
                new User("janedoe", "iloveyou", grantedAuthorityList), "Credentials");

        when(authenticationManager.authenticate((Authentication) any())).thenReturn(testingAuthenticationToken);
        ResponseEntity<AuthResponse> actualLoginResult = authServiceImpl.login(new AuthRequest("janedoe", "iloveyou"));
        assertTrue(actualLoginResult.hasBody());
        assertTrue(actualLoginResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualLoginResult.getStatusCode());
        AuthResponse body = actualLoginResult.getBody();
        assertEquals("ABC123", body.getRefreshToken());
        assertEquals("janedoe", body.getUsername());
        List<String> roles = body.getRoles();
        assertEquals(1, roles.size());
        assertEquals("Role", roles.get(0));
        assertEquals("ABC123", body.getToken());
        verify(jwtUtils).generateJwtToken((UserDetails) any());
        verify(jwtUtils).generateRefreshToken((UserDetails) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#login(AuthRequest)}
     */
    @Test
    void testLogin6() throws AuthenticationException {
        when(jwtUtils.generateJwtToken((UserDetails) any())).thenThrow(new AccountExpiredException("Msg"));
        when(jwtUtils.generateRefreshToken((UserDetails) any())).thenThrow(new AccountExpiredException("Msg"));
        when(authenticationManager.authenticate((Authentication) any())).thenReturn(
                new TestingAuthenticationToken(new User("janedoe", "iloveyou", new ArrayList<>()), "Credentials"));
        assertThrows(AccountExpiredException.class, () -> authServiceImpl.login(new AuthRequest("janedoe", "iloveyou")));
        verify(jwtUtils).generateJwtToken((UserDetails) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#refresh(RefreshRequest)}
     */
    @Test
    void testRefresh() throws UsernameNotFoundException {
        when(jwtUtils.generateJwtToken((UserDetails) any())).thenReturn("ABC123");
        when(jwtUtils.getUserNameFromToken((String) any())).thenReturn("janedoe");
        when(jwtUtils.validateJwtToken((String) any())).thenReturn(true);
        when(userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new SecurityUser("Login", "iloveyou", new ArrayList<>()));
        ResponseEntity<RefreshResponse> actualRefreshResult = authServiceImpl.refresh(new RefreshRequest("ABC123"));
        assertTrue(actualRefreshResult.hasBody());
        assertTrue(actualRefreshResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualRefreshResult.getStatusCode());
        assertEquals("ABC123", actualRefreshResult.getBody().getToken());
        verify(jwtUtils).validateJwtToken((String) any());
        verify(jwtUtils).generateJwtToken((UserDetails) any());
        verify(jwtUtils).getUserNameFromToken((String) any());
        verify(userDetailsService).loadUserByUsername((String) any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#refresh(RefreshRequest)}
     */
    @Test
    void testRefresh2() throws UsernameNotFoundException {
        when(jwtUtils.generateJwtToken((UserDetails) any())).thenReturn("ABC123");
        when(jwtUtils.getUserNameFromToken((String) any())).thenReturn("janedoe");
        when(jwtUtils.validateJwtToken((String) any())).thenReturn(true);
        when(userDetailsService.loadUserByUsername((String) any())).thenThrow(new AccountExpiredException("Msg"));
        assertThrows(AccountExpiredException.class, () -> authServiceImpl.refresh(new RefreshRequest("ABC123")));
        verify(jwtUtils).validateJwtToken((String) any());
        verify(jwtUtils).getUserNameFromToken((String) any());
        verify(userDetailsService).loadUserByUsername((String) any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#refresh(RefreshRequest)}
     */
    @Test
    void testRefresh3() throws UsernameNotFoundException {
        when(jwtUtils.generateJwtToken((UserDetails) any())).thenReturn("ABC123");
        when(jwtUtils.getUserNameFromToken((String) any())).thenReturn("janedoe");
        when(jwtUtils.validateJwtToken((String) any())).thenReturn(false);
        when(userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new SecurityUser("Login", "iloveyou", new ArrayList<>()));
        assertThrows(AccountExpiredException.class, () -> authServiceImpl.refresh(new RefreshRequest("ABC123")));
        verify(jwtUtils).validateJwtToken((String) any());
    }

    /**
     * Method under test: {@link AuthServiceImpl#refresh(RefreshRequest)}
     */
    @Test
    void testRefresh4() throws UsernameNotFoundException {
        when(jwtUtils.generateJwtToken((UserDetails) any())).thenReturn("ABC123");
        when(jwtUtils.getUserNameFromToken((String) any())).thenReturn("janedoe");
        when(jwtUtils.validateJwtToken((String) any())).thenReturn(true);
        when(userDetailsService.loadUserByUsername((String) any()))
                .thenReturn(new SecurityUser("Login", "iloveyou", new ArrayList<>()));
        assertThrows(AccountExpiredException.class, () -> authServiceImpl.refresh(new RefreshRequest(null)));
    }

}

