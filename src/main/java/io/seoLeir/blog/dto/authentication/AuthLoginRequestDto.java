package io.seoLeir.blog.dto.authentication;

import io.seoLeir.blog.validation.user.Username;
import io.seoLeir.blog.validation.user.Password;
import lombok.Value;

@Value
public class AuthLoginRequestDto {

    @Username
    String username;

    @Password
    String password;

}
