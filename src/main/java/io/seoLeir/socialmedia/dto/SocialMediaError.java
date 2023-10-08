package io.seoLeir.socialmedia.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;

@Builder
public class SocialMediaError {
    HttpStatusCode statusCode;
    Instant errorDateTime;
    String errorDescription;
    String path;
}
