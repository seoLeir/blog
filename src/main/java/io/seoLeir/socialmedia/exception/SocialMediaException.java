package io.seoLeir.socialmedia.exception;


import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;

@Getter
public abstract class SocialMediaException extends RuntimeException {
    private final HttpStatusCode httpStatusCode;
    private final Instant timestamp;


    public SocialMediaException(String message, HttpStatusCode httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
        this.timestamp = Instant.now();
    }
}
