package io.seoLeir.socialmedia.dto.publication;


import lombok.Value;
import org.springframework.util.MimeType;

import java.util.UUID;

public record CreatePublicationDto(String text,
                                   String header,
                                   UUID filename) {
}
