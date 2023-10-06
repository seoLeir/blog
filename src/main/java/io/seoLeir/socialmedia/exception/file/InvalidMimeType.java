package io.seoLeir.socialmedia.exception.file;


import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class InvalidMimeType extends SocialMediaException {
    public InvalidMimeType(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
