package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.service.UserSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users/{username}")
@RequiredArgsConstructor
public class UserSubscriptionRestController {
    private final UserSubscriptionService userSubscriptionService;

    @GetMapping("/followers")
    PageResponseDto<String> getUserFollowers(@PathVariable("username") String username){
//        TODO 02.11.2023
        return null;
    }
    @GetMapping("/following")
    PageResponseDto<String> getUserFollowing(@PathVariable("username") String username){
//        TODO 02.11.2023
        return null;
    }

}
