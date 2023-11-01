package io.seoLeir.socialmedia.exception.comment;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class CommentNotFoundException extends SocialMediaException {
    public CommentNotFoundException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
