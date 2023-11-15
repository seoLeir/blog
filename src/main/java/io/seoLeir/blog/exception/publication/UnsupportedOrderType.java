package io.seoLeir.blog.exception.publication;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class UnsupportedOrderType extends SocialMediaException {
    public UnsupportedOrderType(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
