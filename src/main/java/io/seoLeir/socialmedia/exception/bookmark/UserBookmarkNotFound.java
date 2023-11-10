package io.seoLeir.socialmedia.exception.bookmark;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class UserBookmarkNotFound extends SocialMediaException {
    public UserBookmarkNotFound(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
