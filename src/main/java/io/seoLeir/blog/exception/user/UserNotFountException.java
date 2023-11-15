package io.seoLeir.blog.exception.user;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class UserNotFountException extends SocialMediaException {

    public UserNotFountException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
