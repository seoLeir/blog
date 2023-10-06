package io.seoLeir.socialmedia.exception.user;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class UserNotFountException extends SocialMediaException {

    public UserNotFountException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
