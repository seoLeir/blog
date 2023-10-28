package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.user.UserProfileResponseDto;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserInfoService {
    private final UserBookmarkService userBookmarkService;
    private final PublicationCommentService publicationCommentService;
    private final PublicationService publicationService;
    private final UserRepository userRepository;

    @Transactional
    public UserProfileResponseDto getUserProfile(String username) {
        UUID userUuid = userRepository.getUserUuidByUsername(username);
        long userBookmarkedPublicationsCount = userBookmarkService.getUserAllPublicationsCount(username);
        long userAllCommentsCount = publicationCommentService.publicationCommentsByUserUuid(
                userRepository.getUserUuidByUsername(username));
        long allPublicationsByUsername = publicationService.getAllPublicationsCountByUsername(userUuid);
        return userRepository.findByUsername(username).stream()
                .map(user -> new UserProfileResponseDto(
                        user.getUsername(), user.getEmail(), user.getInfo(), user.getCreatedAt(),
                        userBookmarkedPublicationsCount, userAllCommentsCount, allPublicationsByUsername))
                .findFirst()
                .orElseThrow(() -> new UserNotFountException("User with username:" + username + " not found",
                        HttpStatusCode.valueOf(404)));
    }
}
