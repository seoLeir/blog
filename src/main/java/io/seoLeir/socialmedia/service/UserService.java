package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.user.UserProfileResponseDto;
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

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserBookmarkService userBookmarkService;
    private final PublicationCommentService publicationCommentService;
//    private final PublicationService publicationService;

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
                .map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList()))
                .orElseThrow(() -> {
                    log.debug("user with username: {} not found", username);
                    return new UsernameNotFoundException("User with username: " + username + "not found");
                });
    }

    @Transactional
    public void update(String username, Roles role){
        userRepository.updateRole(username, role);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

//    @Transactional
//    public UserProfileResponseDto getUserProfile(String username) {
//        long userBookmarkedPublicationsCount = userBookmarkService.getUserAllPublicationsCount(username);
//        long userAllCommentsCount = publicationCommentService.publicationCommentsByUserUuid(userRepository.getUserUuidByUsername(username));
//        long allPublicationsByUsername = publicationService.getAllPublicationsCountByUsername(username);
//        return userRepository.findByUsername(username).stream()
//                .map(user -> new UserProfileResponseDto(
//                        user.getUsername(), user.getEmail(), user.getInfo(), user.getCreatedAt(),
//                        userBookmarkedPublicationsCount, userAllCommentsCount, allPublicationsByUsername))
//                .findFirst()
//                .orElseThrow(() -> new UserNotFountException("User with username:" + username + " not found",
//                        HttpStatusCode.valueOf(404)));
//    }

    @Transactional
    public boolean isUserExists(String username){
        return userRepository.existsByUsername(username);
    }
}
