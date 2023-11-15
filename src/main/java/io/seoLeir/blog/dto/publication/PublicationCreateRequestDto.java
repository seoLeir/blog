package io.seoLeir.blog.dto.publication;


import io.seoLeir.blog.validation.publication.PublicationText;
import io.seoLeir.blog.validation.publication.PublicationTitle;

import java.util.List;
import java.util.UUID;

public record PublicationCreateRequestDto(
        @PublicationText
        String text,
        @PublicationTitle
        String header,
        List<UUID> files) {
}
