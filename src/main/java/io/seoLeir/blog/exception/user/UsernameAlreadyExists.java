package io.seoLeir.blog.exception.user;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class UsernameAlreadyExists extends SocialMediaException {
    public UsernameAlreadyExists(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
