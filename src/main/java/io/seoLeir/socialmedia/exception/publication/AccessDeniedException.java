package io.seoLeir.socialmedia.exception.publication;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class AccessDeniedException extends SocialMediaException {

    public AccessDeniedException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
