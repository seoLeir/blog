package io.seoLeir.socialmedia.exception;


import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public abstract class SocialMediaException extends RuntimeException {
    private final HttpStatusCode httpStatusCode;


    public SocialMediaException(String message, HttpStatusCode httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }
}
