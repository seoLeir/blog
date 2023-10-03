package io.seoLeir.socialmedia.exception.user;

public class InvalidUsernameOrPassword extends RuntimeException{
    public InvalidUsernameOrPassword(String message) {
        super(message);
    }

}
