package io.seoLeir.blog.exception.file;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends SocialMediaException {
    public FileNotFoundException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
