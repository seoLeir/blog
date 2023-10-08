package io.seoLeir.socialmedia.exception;

import io.seoLeir.socialmedia.dto.SocialMediaError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(SocialMediaException.class)
    public SocialMediaError handleSocialMediaException(SocialMediaException ex, WebRequest webRequest) {
        return SocialMediaError.builder()
                .statusCode(ex.getHttpStatusCode())
                .errorDateTime(ex.getTimestamp())
                .errorDescription(ex.getMessage())
                .path(webRequest.getContextPath())
                .build();
    }
}
