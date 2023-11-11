package io.seoLeir.socialmedia.dto.authentication;

import io.seoLeir.socialmedia.validation.user.Password;
import io.seoLeir.socialmedia.validation.user.Username;
import lombok.Value;

@Value
public class AuthLoginRequestDto {

    @Username
    String username;

    @Password
    String password;

}
