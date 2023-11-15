package io.seoLeir.blog.exception.bookmark;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class UserBookmarkNotFound extends SocialMediaException {
    public UserBookmarkNotFound(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
