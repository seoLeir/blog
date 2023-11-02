package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.comment.PublicationCommentGetResponseDto;
import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.dto.user.UserProfileResponseDto;
import io.seoLeir.socialmedia.entity.*;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    private final SubscriptionService subscriptionService;

    /**
    * Approved
    */
    @GetMapping("/{username}/profile")
    public UserProfileResponseDto getUserProfile(@PathVariable("username") String username, Principal principal){
        return userInfoService.getUserProfile(username, principal.getName());
    }

    /**
    * Approved
    */
    @PostMapping("/{username}/subscribe")
    @ResponseStatus(HttpStatus.OK)
    public void follow(@PathVariable("username") String username, Principal principal){
        if (!username.equals(principal.getName()))
            subscriptionService.subscribe(principal.getName(), username);
    }

    /**
     * Approved
     */
    @PostMapping("/{username}/unsubscribe")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollow(@PathVariable("username") String username, Principal principal){
        if (!username.equals(principal.getName()))
            subscriptionService.unsubscribe(principal.getName(), username);
    }

    /**
     * Approved
     */
    @PatchMapping("/{username}/role")
    public void updateUserRole(@PathVariable("username") String username,
                               @RequestParam("role") String role){
        userService.update(username, role);
    }

    /**
     * Approved
     */
    @GetMapping("/{username}/publications")
    public PageResponseDto<PublicationGetResponseDto> getUsersAllPublication(@PathVariable("username") String username,
                                                               @RequestBody PageRequestDto requestDto,
                                                               @RequestParam(value = "text", required = false) String textToSearch){
        return publicationService.getAllUserPublications(username, requestDto, textToSearch);
    }

    @GetMapping("/{username}/comments")
    public PageResponseDto<PublicationCommentGetResponseDto> getUserAllComments(@PathVariable("username") String username,
                                                                                @RequestBody PageRequestDto pageRequestDto){
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        return publicationCommentService.getUserComments(user.getId(), pageRequestDto);
    }


    @GetMapping("/{username}/bookmarks")
    public PageResponseDto<PublicationGetResponseDto> getUserBookmarks(@PathVariable("username") String username,
                                                                       @RequestBody PageRequestDto pageRequestDto ){
        if (!userService.isUserExists(username))
            throw new UserNotFountException("User not found", HttpStatusCode.valueOf(404));
        List<UUID> useAllBookmarkedPublicationsUuid =
                userBookmarkService.getUseAllBookmarkedPublicationsUuid(userService.getUserUuidFromUsername(username)
                        .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404))));
        return publicationService.getAllUserBookmarkedPublication(useAllBookmarkedPublicationsUuid, pageRequestDto);
    }

    @GetMapping("/{username}/followers")
    PageResponseDto<String> getUserFollowers(@PathVariable("username") String username, @RequestBody PageRequestDto dto){
        return subscriptionService.getUserFollowers(username, dto);
    }
    
    @GetMapping("/{username}/following")
    PageResponseDto<String> getUserFollowing(@PathVariable("username") String username, @RequestBody PageRequestDto dto){
        return subscriptionService.getUserFollowing(username, dto);
    }
}
