package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.entity.Roles;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.exception.user.InvalidUsernameOrPassword;
import io.seoLeir.socialmedia.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
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
    public UUID registerUser(String username, String email, String password){
        UUID userUuid = UUID.randomUUID();
        userService.save(new User(userUuid, username, email, passwordEncoder.encode(password), Roles.ROLE_USER));
        return userUuid;
    }

    @Transactional
    public String login(String username, String password){
        UserDetails userDetails = userService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, userDetails.getPassword()))
            return jwtTokenUtils.generateToken(userDetails.getUsername(), userDetails.getAuthorities());
        else
            throw new InvalidUsernameOrPassword("Username or password are invalid", HttpStatusCode.valueOf(401));
    }
}
