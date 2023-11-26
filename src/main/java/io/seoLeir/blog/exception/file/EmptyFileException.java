package io.seoLeir.blog.exception.file;


import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class EmptyFileException extends SocialMediaException {
    public EmptyFileException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
