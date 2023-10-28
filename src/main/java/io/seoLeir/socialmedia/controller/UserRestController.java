package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.comment.PublicationUserCommentsDto;
import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.dto.user.UserProfileResponseDto;
import io.seoLeir.socialmedia.entity.*;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final PublicationService publicationService;
    private final PublicationCommentService publicationCommentService;
    private final UserBookmarkService userBookmarkService;
    private final UserInfoService userInfoService;

    @GetMapping("/{username}/profile")
    public UserProfileResponseDto getUserProfile(@PathVariable("username") String username){
        return userInfoService.getUserProfile(username);
    }

    @PatchMapping("/{username}/profile")
    public void updateUserRole(@PathVariable("username") String username,
                               @RequestParam("role") String role){
        userService.update(username, role);
    }

    @GetMapping("/{username}/publications")
    public PageResponseDto<PublicationGetResponseDto> getUsersAllPublication(@PathVariable("username") String username,
                                                               @RequestBody PageRequestDto requestDto,
                                                               @RequestParam(value = "text", required = false) String textToSearch){
        return publicationService.getAllUserPublications(username, requestDto, textToSearch);
    }

    @GetMapping("/{username}/comments")
    public PageResponseDto<PublicationUserCommentsDto> getUserAllComments(@PathVariable("username") String username,
                                                                          @RequestBody PageRequestDto pageRequestDto){
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        return publicationCommentService.publicationCommentPageResponse(user.getId(), pageRequestDto);
    }


    @GetMapping("/{username}/bookmarks")
    public PageResponseDto<PublicationGetResponseDto> getUserBookmarks(@PathVariable("username") String username,
                                                                       Pageable pageable){
        log.info("method getUserBookmarks() was called");
        if (!userService.isUserExists(username))
            throw new UserNotFountException("User not found", HttpStatusCode.valueOf(404));
        List<UUID> useAllBookmarkedPublicationsUuid =
                userBookmarkService.getUseAllBookmarkedPublicationsUuid(userService.getUserUuidFromUsername(username)
                        .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404))));
        log.info("{}'s bookmarked publications uuids: {}", username, useAllBookmarkedPublicationsUuid);
        return publicationService.getAllUserBookmarkedPublication(useAllBookmarkedPublicationsUuid, pageable);
    }
}
