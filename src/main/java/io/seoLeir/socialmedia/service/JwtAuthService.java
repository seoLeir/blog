package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.entity.Roles;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.entity.UserRole;
import io.seoLeir.socialmedia.exception.user.InvalidUsernameOrPassword;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtAuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtTokenUtils jwtTokenUtils;
    private final UserRoleService userRoleService;

    @Transactional
    public UUID registerUser(String username, String email, String password){
        Roles userRole = roleService.findByName("ROLE_USER");
        User user = new User(UUID.randomUUID(), username, email, passwordEncoder.encode(password));
        userService.save(user);
        userRoleService.save(new UserRole(user, userRole));
        return user.getId();
    }

    @Transactional
    public String login(String username, String password){
        UserDetails userDetails = userService.loadUserByUsername(username);
        User foundUser = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userRoleService.getUserAuthorities(foundUser.getId())
                .forEach(authority -> {
                    authorities.add(new SimpleGrantedAuthority(authority));
                });
        if (passwordEncoder.matches(password, userDetails.getPassword()))
            return jwtTokenUtils.generateToken(userDetails.getUsername(), authorities);
        else
            throw new InvalidUsernameOrPassword("Username or password are invalid",
                    HttpStatusCode.valueOf(401));
    }
}
