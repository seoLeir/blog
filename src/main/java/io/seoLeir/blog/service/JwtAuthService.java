package io.seoLeir.blog.service;

import io.seoLeir.blog.util.JwtTokenUtils;
import io.seoLeir.blog.entity.Roles;
import io.seoLeir.blog.entity.User;
import io.seoLeir.blog.entity.UserRole;
import io.seoLeir.blog.exception.user.InvalidUsernameOrPassword;
import io.seoLeir.blog.exception.user.UserNotFountException;
import io.seoLeir.blog.exception.user.UsernameAlreadyExists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        if(userService.isUserExists(username)){
            throw new UsernameAlreadyExists("User with" + username + " + username already exists",
                    HttpStatusCode.valueOf(409));
        }
        User user = new User(UUID.randomUUID(), username, email, passwordEncoder.encode(password));
        userService.save(user);
        userRoleService.save(new UserRole(user, userRole));
        return user.getId();
    }

    @Transactional(readOnly = true)
    public String login(String username, String password){
        UserDetails userDetails = userService.loadUserByUsername(username);
        User foundUser = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userRoleService.getUserAuthorities(foundUser.getId())
                .forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority)));
        if (passwordEncoder.matches(password, userDetails.getPassword()))
            return jwtTokenUtils.generateToken(userDetails.getUsername(), authorities);
        else
            throw new InvalidUsernameOrPassword("Username or password are incorrect",
                    HttpStatusCode.valueOf(401));
    }
}
