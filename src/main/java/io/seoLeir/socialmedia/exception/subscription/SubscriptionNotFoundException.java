package io.seoLeir.socialmedia.exception.subscription;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class SubscriptionNotFoundException extends SocialMediaException {
    public SubscriptionNotFoundException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
