package io.seoLeir.socialmedia.exception.file;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class EmptyFileException extends RuntimeException{
    public EmptyFileException(String message) {
        super(message);
    }
}
