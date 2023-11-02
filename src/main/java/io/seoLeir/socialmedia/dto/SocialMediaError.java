package io.seoLeir.socialmedia.dto;

import lombok.Builder;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;
import java.time.LocalDate;

@Builder
public class SocialMediaError {
    HttpStatusCode statusCode;
    Instant errorDateTime;
    String errorDescription;
    String path;
}
