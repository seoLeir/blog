package io.seoLeir.socialmedia.exception.file;


import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class EmptyFileException extends SocialMediaException {
    public EmptyFileException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
