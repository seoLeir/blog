package io.seoLeir.blog.exception.comment;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class CommentNotFoundException extends SocialMediaException {
    public CommentNotFoundException(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
