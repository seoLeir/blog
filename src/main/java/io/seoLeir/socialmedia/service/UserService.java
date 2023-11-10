package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.entity.Roles;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.exception.user.EmailAlreadyExists;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.exception.user.UsernameAlreadyExists;
import io.seoLeir.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Transactional
    public void save(User user){
        if (userRepository.existsByUsername(user.getUsername())){
            throw new UsernameAlreadyExists("User with this username already exists", HttpStatusCode.valueOf(401));
        }
        if (userRepository.existsByEmail(user.getUsername())){
            throw new EmailAlreadyExists("User with this email already exists", HttpStatusCode.valueOf(401));
        }
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.debug("user with username: {} not found", username);
                    return new UsernameNotFoundException("User with username: " + username + "not found");
                });
    }

    @Transactional
    public void update(String username, String role){
        User foundUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFountException("User with username:" + username + " not found",
                        HttpStatusCode.valueOf(404)));
        Roles foundRole = roleService.findByName(role);
        userRoleService.update(foundRole.getId(),foundUser.getId());
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public boolean isUserExists(String username){
        return userRepository.existsByUsername(username);
    }

    @Transactional(readOnly = true)
    public Optional<UUID> getUserUuidFromUsername(String username){
        return userRepository.getByUsername(username);
    }
}
