package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.comment.PublicationUserCommentsDto;
import io.seoLeir.socialmedia.dto.message.MessageChatControllerDto;
import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.user.UserProfileResponseDto;
import io.seoLeir.socialmedia.entity.*;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final PublicationService publicationService;
    private final PublicationCommentService publicationCommentService;
    private final UserBookmarkService userBookmarkService;
    private final UserMessageService userMessageService;

//    @GetMapping("/{username}/profile")
//    public UserProfileResponseDto getUserProfile(@PathVariable("username") String username){
////        return userService.getUserProfile(username);
//        return null;
//    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/{username}/profile")
    public void updateUserRole(@PathVariable("username") String username,
                               @RequestParam("role") Roles role){
        userService.update(username, role);
    }

    @GetMapping("/{username}/publications")
    public PageResponseDto<Publication> getUsersAllPublication(@PathVariable("username") String username,
                                                               @RequestBody PageRequestDto requestDto,
                                                               @RequestParam("text") String textToSearch){
        return publicationService.getAllUserPublications(username, requestDto, textToSearch);
    }

    @GetMapping("/{username}/messages")
    public PageResponseDto<MessageChatControllerDto> getUserAllDialogues(Principal principal,
                                                                         @RequestBody PageRequestDto pageRequestDto){
        return userMessageService.getUserDialogues(principal.getName(), pageRequestDto);
    }

    @GetMapping("/{username}/comments")
    public PageResponseDto<PublicationUserCommentsDto> getAllUserCommentsUnderPublication(
            @PathVariable("username") String username,
            @RequestBody PageRequestDto pageRequestDto){
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        return publicationCommentService.publicationCommentPageResponse(user.getId(), pageRequestDto);
    }

    @GetMapping("/{username}/bookmarks")
    public PageResponseDto<Publication> getUserBookmarks(
            @PathVariable("username") String username,
            PageRequestDto requestDto){
        if (userService.isUserExists(username))
            return userBookmarkService.getUseAllBookmarkedPublicationsUuid(username, requestDto);
        else
            throw new UserNotFountException("User not found", HttpStatusCode.valueOf(404));
    }
}
