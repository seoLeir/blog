package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.user.UserProfileResponseDto;
import io.seoLeir.socialmedia.entity.Roles;
import io.seoLeir.socialmedia.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping("/{username}")
    public UserProfileResponseDto getUserProfile(@PathVariable("username") String username){
        return userService.findUser(username);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @GetMapping("/{username}")
    public void updateUserRole(@PathVariable("username") String username,
                               @RequestParam("role") Roles role){
        userService.update(username, role);
    }
}
