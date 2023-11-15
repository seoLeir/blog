package io.seoLeir.blog.exception.role;

import io.seoLeir.blog.exception.SocialMediaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;

@Slf4j
public class RoleNotFoundException extends SocialMediaException {
    public RoleNotFoundException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
