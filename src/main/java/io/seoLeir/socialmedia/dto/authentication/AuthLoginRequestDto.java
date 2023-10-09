package io.seoLeir.socialmedia.dto.authentication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class AuthLoginRequestDto {

    @NotBlank
    @Size(min = 5, max = 32)
    String username;

    @NotBlank
    @Size(min = 8)
    String password;

}
