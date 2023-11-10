package io.seoLeir.socialmedia.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;
import java.time.LocalDate;

@Builder
@Data
public class SocialMediaError {
    Integer statusCode;
    Instant errorDateTime;
    String errorDescription;
    String path;
}
