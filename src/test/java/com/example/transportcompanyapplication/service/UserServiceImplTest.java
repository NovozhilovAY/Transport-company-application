package com.example.transportcompanyapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.repository.UserRepository;
import com.example.transportcompanyapplication.util.PatchMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PatchMapper<User> patchMapper;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#save(User)}
     */
    @Test
    void testSave() {
        User user = new User();
        user.setId(1);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        when(userRepository.save((User) any())).thenReturn(user);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        User user1 = new User();
        user1.setId(1);
        user1.setLogin("Login");
        user1.setPassword("iloveyou");
        user1.setRoles(new ArrayList<>());
        assertSame(user, userServiceImpl.save(user1));
        verify(userRepository).save((User) any());
        verify(passwordEncoder).encode((CharSequence) any());
        assertEquals("secret", user1.getPassword());
    }

    /**
     * Method under test: {@link UserServiceImpl#update(User, Integer)}
     */
    @Test
    void testUpdate() {
        User user = new User();
        user.setId(1);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(userRepository).update((User) any());
        when(userRepository.findById((Integer) any())).thenReturn(ofResult);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        User user1 = new User();
        user1.setId(1);
        user1.setLogin("Login");
        user1.setPassword("iloveyou");
        user1.setRoles(new ArrayList<>());
        assertSame(user, userServiceImpl.update(user1, 1));
        verify(userRepository, atLeast(1)).findById((Integer) any());
        verify(userRepository).update((User) any());
        verify(passwordEncoder).encode((CharSequence) any());
        assertEquals("secret", user1.getPassword());
    }

    /**
     * Method under test: {@link UserServiceImpl#partialUpdate(User, Integer)}
     */
    @Test
    void testPartialUpdate() {
        User user = new User();
        user.setId(1);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(userRepository).update((User) any());
        when(userRepository.findById((Integer) any())).thenReturn(ofResult);
        doNothing().when(patchMapper).update((User) any(), (User) any());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        User user1 = new User();
        user1.setId(1);
        user1.setLogin("Login");
        user1.setPassword("iloveyou");
        user1.setRoles(new ArrayList<>());
        User actualPartialUpdateResult = userServiceImpl.partialUpdate(user1, 1);
        assertSame(user, actualPartialUpdateResult);
        assertEquals("secret", actualPartialUpdateResult.getPassword());
        verify(userRepository, atLeast(1)).findById((Integer) any());
        verify(userRepository).update((User) any());
        verify(patchMapper).update((User) any(), (User) any());
        verify(passwordEncoder, atLeast(1)).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#partialUpdate(User, Integer)}
     */
    @Test
    void testPartialUpdate2() {
        User user = new User();
        user.setId(1);
        user.setLogin("Login");
        user.setPassword("secret");
        user.setRoles(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(userRepository).update((User) any());
        when(userRepository.findById((Integer) any())).thenReturn(ofResult);
        doNothing().when(patchMapper).update((User) any(), (User) any());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        User user1 = new User();
        user1.setId(1);
        user1.setLogin("Login");
        user1.setPassword("iloveyou");
        user1.setRoles(new ArrayList<>());
        assertSame(user, userServiceImpl.partialUpdate(user1, 1));
        verify(userRepository, atLeast(1)).findById((Integer) any());
        verify(userRepository).update((User) any());
        verify(patchMapper).update((User) any(), (User) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#partialUpdate(User, Integer)}
     */
    @Test
    void testPartialUpdate6() {
        User user = new User();
        user.setId(1);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        user.setRoles(new ArrayList<>());
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(userRepository).update((User) any());
        when(userRepository.findById((Integer) any())).thenReturn(ofResult);
        doNothing().when(patchMapper).update((User) any(), (User) any());
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        User user1 = new User();
        user1.setId(1);
        user1.setLogin("Login");
        user1.setPassword(null);
        user1.setRoles(new ArrayList<>());
        assertSame(user, userServiceImpl.partialUpdate(user1, 1));
        verify(userRepository, atLeast(1)).findById((Integer) any());
        verify(userRepository).update((User) any());
        verify(patchMapper).update((User) any(), (User) any());
    }
}

