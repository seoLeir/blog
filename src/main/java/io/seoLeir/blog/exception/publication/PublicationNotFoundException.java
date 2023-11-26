package io.seoLeir.blog.exception.publication;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class PublicationNotFoundException extends SocialMediaException {

    public PublicationNotFoundException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
