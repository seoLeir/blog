package io.seoLeir.socialmedia.dto;

import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;

@Data
public class SocialMediaError {
    private final HttpStatusCode statusCode;
    private Instant errorDateTime;
    private String errorDescription;
}
