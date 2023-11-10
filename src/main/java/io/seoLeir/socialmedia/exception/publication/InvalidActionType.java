package io.seoLeir.socialmedia.exception.publication;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class InvalidActionType extends SocialMediaException {
    public InvalidActionType(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
