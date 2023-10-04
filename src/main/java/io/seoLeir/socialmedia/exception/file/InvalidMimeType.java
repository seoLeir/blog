package io.seoLeir.socialmedia.exception.file;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class InvalidMimeType extends RuntimeException{
    public InvalidMimeType(String message) {
        super(message);
    }
}
