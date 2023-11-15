package io.seoLeir.blog.exception.file;


import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class InvalidMimeType extends SocialMediaException {
    public InvalidMimeType(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
