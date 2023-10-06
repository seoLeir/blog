package io.seoLeir.socialmedia.exception.publication;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class PublicationNotFound extends SocialMediaException {

    public PublicationNotFound(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
