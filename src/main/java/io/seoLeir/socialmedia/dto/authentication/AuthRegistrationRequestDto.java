package io.seoLeir.socialmedia.dto.authentication;

import io.seoLeir.socialmedia.validation.user.Password;
import io.seoLeir.socialmedia.validation.user.Username;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class AuthRegistrationRequestDto {

    @Username
    String username;

    @Email(message = "Invalid email")
    String email;

    @Password
    String password;
}
