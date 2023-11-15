package io.seoLeir.blog.exception.publication;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class InvalidActionType extends SocialMediaException {
    public InvalidActionType(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
