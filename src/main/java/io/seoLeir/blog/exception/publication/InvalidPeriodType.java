package io.seoLeir.blog.exception.publication;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class InvalidPeriodType extends SocialMediaException {
    public InvalidPeriodType(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
