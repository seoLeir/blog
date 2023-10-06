package io.seoLeir.socialmedia.exception.user;

import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class InvalidUsernameOrPassword extends SocialMediaException {


    public InvalidUsernameOrPassword(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
