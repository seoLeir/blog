package io.seoLeir.socialmedia.exception.user;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class UsernameAlreadyExists extends SocialMediaException {
    public UsernameAlreadyExists(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
