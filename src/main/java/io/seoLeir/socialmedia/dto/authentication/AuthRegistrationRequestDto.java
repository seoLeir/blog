package io.seoLeir.socialmedia.dto.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class AuthRegistrationRequestDto {


    @NotNull(message = "Username should not be null")
    @Size(min = 5, max = 32, message = "Username must have at least 5 characters and no more than 32 characters")
    String username;

    @Email
    String email;

    @NotBlank
    @Size(min = 8)
    String password;
}
