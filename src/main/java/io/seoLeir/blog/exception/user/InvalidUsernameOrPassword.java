package io.seoLeir.blog.exception.user;

import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class InvalidUsernameOrPassword extends SocialMediaException {


    public InvalidUsernameOrPassword(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
