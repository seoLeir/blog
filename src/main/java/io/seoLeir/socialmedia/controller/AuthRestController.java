package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.authentication.AuthLoginRequestDto;
import io.seoLeir.socialmedia.dto.authentication.AuthRegistrationRequestDto;
import io.seoLeir.socialmedia.dto.authentication.AuthJwtTokenResponseDto;
import io.seoLeir.socialmedia.service.JwtAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthRestController {
    private final JwtAuthService authService;

    @PostMapping("/registration")
    public HttpStatusCode authenticateAndGetToken(@RequestBody AuthRegistrationRequestDto authRequest){
        authService.registerUser(authRequest.getUsername(), authRequest.getEmail(), authRequest.getPassword());
        return HttpStatusCode.valueOf(201);
    }

    @PostMapping("/login")
    public AuthJwtTokenResponseDto login(@RequestBody AuthLoginRequestDto requestDto){
        return new AuthJwtTokenResponseDto(authService.authorize(requestDto.getUsername(), requestDto.getPassword()));
    }
}
