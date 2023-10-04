package io.seoLeir.socialmedia.exception.file;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class UnsupportedFileExtension extends RuntimeException{
    public UnsupportedFileExtension(String message) {
        super(message);
    }
}
