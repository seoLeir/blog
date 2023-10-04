package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.authentication.AuthLoginRequestDto;
import io.seoLeir.socialmedia.dto.authentication.AuthRegistrationRequestDto;
import io.seoLeir.socialmedia.dto.authentication.AuthJwtTokenResponseDto;
import io.seoLeir.socialmedia.dto.authentication.AuthRegistrationResponseDto;
import io.seoLeir.socialmedia.service.JwtAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthRestController {
    private final JwtAuthService authService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthRegistrationResponseDto authenticateAndGetToken(@RequestBody AuthRegistrationRequestDto authRequest){
        UUID createdUserUuid = authService.registerUser(authRequest.getUsername(), authRequest.getEmail(), authRequest.getPassword());
        return new AuthRegistrationResponseDto(createdUserUuid);
    }

    @PostMapping("/login")
    public AuthJwtTokenResponseDto login(@RequestBody AuthLoginRequestDto requestDto){
        return new AuthJwtTokenResponseDto(authService.login(requestDto.getUsername(), requestDto.getPassword()));
    }
}
