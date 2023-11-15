package io.seoLeir.blog.dto.authentication;

import io.seoLeir.blog.validation.user.Username;
import io.seoLeir.blog.validation.user.Password;
import jakarta.validation.constraints.Email;
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
