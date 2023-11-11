package io.seoLeir.socialmedia.dto.publication;


import io.seoLeir.socialmedia.validation.publication.PublicationText;
import io.seoLeir.socialmedia.validation.publication.PublicationTitle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record PublicationCreateRequestDto(
        @PublicationText
        String text,
        @PublicationTitle
        String header,
        List<UUID> files) {
}
