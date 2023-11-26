package io.seoLeir.blog.exception.publication;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class AccessDeniedException extends SocialMediaException {

    public AccessDeniedException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
