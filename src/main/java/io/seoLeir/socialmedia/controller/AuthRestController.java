package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.authentication.AuthLoginRequestDto;
import io.seoLeir.socialmedia.dto.authentication.AuthRegistrationRequestDto;
import io.seoLeir.socialmedia.dto.authentication.AuthJwtTokenResponseDto;
import io.seoLeir.socialmedia.dto.user.UserCreated;
import io.seoLeir.socialmedia.service.JwtAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthRestController {
    private final JwtAuthService authService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreated authenticateAndGetToken(@RequestBody AuthRegistrationRequestDto authRequest){
        authService.registerUser(authRequest.getUsername(), authRequest.getEmail(), authRequest.getPassword());
        return new UserCreated(HttpStatus.CREATED, "User was successfully created");
    }

    @PostMapping("/login")
    public AuthJwtTokenResponseDto login(@RequestBody AuthLoginRequestDto requestDto){
        return new AuthJwtTokenResponseDto(authService.login(requestDto.getUsername(), requestDto.getPassword()));
    }
}
