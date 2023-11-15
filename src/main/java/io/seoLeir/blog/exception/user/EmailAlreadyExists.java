package io.seoLeir.blog.exception.user;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class EmailAlreadyExists extends SocialMediaException {
    public EmailAlreadyExists(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
