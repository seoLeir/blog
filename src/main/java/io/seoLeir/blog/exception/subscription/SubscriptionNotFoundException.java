package io.seoLeir.blog.exception.subscription;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class SubscriptionNotFoundException extends SocialMediaException {
    public SubscriptionNotFoundException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
