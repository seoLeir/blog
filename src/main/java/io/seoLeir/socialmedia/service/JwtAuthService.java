package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtAuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;

    @Transactional
    public void registerUser(String username, String email, String password){
        userService.save(new User(UUID.randomUUID(), username, email, passwordEncoder.encode(password)));
    }

    @Transactional
    public String authorize(String username, String password){
        UserDetails userDetails = userService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword())){
            return jwtTokenUtils.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
        }else {
            throw new RuntimeException();
        }
    }
}
