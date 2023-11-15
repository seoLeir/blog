package io.seoLeir.blog.controller;

import io.seoLeir.blog.service.JwtAuthService;
import io.seoLeir.blog.dto.authentication.AuthLoginRequestDto;
import io.seoLeir.blog.dto.authentication.AuthRegistrationRequestDto;
import io.seoLeir.blog.dto.authentication.AuthJwtTokenResponseDto;
import io.seoLeir.blog.dto.authentication.AuthRegistrationResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthRestController {
    private final JwtAuthService authService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthRegistrationResponseDto authenticateAndGetToken(
            @Valid @RequestBody AuthRegistrationRequestDto authRequest){
        UUID createdUserUuid =
                authService.registerUser(authRequest.getUsername(), authRequest.getEmail(), authRequest.getPassword());
        return new AuthRegistrationResponseDto(createdUserUuid);
    }

    @PostMapping("/login")
    public AuthJwtTokenResponseDto login(@Valid @RequestBody AuthLoginRequestDto requestDto){
        return new AuthJwtTokenResponseDto(authService.login(requestDto.getUsername(), requestDto.getPassword()));
    }
}
