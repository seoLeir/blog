package io.seoLeir.socialmedia.exception.role;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;

@Slf4j
public class RoleNotFoundException extends SocialMediaException {
    public RoleNotFoundException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
