package io.seoLeir.socialmedia.exception.user;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class EmailAlreadyExists extends SocialMediaException {
    public EmailAlreadyExists(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
