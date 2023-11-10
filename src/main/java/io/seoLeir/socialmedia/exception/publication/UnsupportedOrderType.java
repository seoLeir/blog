package io.seoLeir.socialmedia.exception.publication;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class UnsupportedOrderType extends SocialMediaException {
    public UnsupportedOrderType(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
