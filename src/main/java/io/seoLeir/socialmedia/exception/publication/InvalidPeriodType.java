package io.seoLeir.socialmedia.exception.publication;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class InvalidPeriodType extends SocialMediaException {
    public InvalidPeriodType(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
