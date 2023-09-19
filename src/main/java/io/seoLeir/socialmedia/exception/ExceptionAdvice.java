package io.seoLeir.socialmedia.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(SocialMediaException.class)
    public void handleSocialMediaException(){

    }
}
