package io.seoLeir.blog.service;

import io.seoLeir.blog.dto.user.UserProfileResponseDto;
import io.seoLeir.blog.exception.user.UserNotFountException;
import io.seoLeir.blog.mapper.UserMapper;
import io.seoLeir.blog.repository.UserRepository;
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
    private final SubscriptionService subscriptionService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserProfileResponseDto getUserProfile(String username, String principleName) {
        UUID userUuid = userRepository.getUserUuidByUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        UUID principleUuid = userRepository.getUserUuidByUsername(principleName)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        long userBookmarkedPublicationsCount = userBookmarkService.getUserAllPublicationsCount(username);
        long userAllCommentsCount = publicationCommentService.getPublicationCommentsCountByUserUuid(userUuid);
        long allPublicationsByUsername = publicationService.getAllPublicationsCountByUsername(userUuid);
        long userFollowers = subscriptionService.getUserFollowersCount(userUuid);
        long userFollowing = subscriptionService.getUserFollowingCount(userUuid);
        Boolean isUserFollowed = subscriptionService.isUserFollowed(principleUuid, userUuid);
        return userRepository.findByUsername(username).stream()
                .map(user -> userMapper.getResponseDtoFromPublicationComment(
                        user,
                        allPublicationsByUsername,
                        userAllCommentsCount,
                        userBookmarkedPublicationsCount,
                        userFollowers,
                        userFollowing,
                        isUserFollowed))
                .findFirst()
                .orElseThrow(() -> new UserNotFountException("User with username:" + username + " not found",
                        HttpStatusCode.valueOf(404)));
    }
}
