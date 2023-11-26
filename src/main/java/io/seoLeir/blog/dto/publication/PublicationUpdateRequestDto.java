package io.seoLeir.blog.dto.publication;

import io.seoLeir.blog.validation.publication.PublicationText;
import io.seoLeir.blog.validation.publication.PublicationTitle;

public record PublicationUpdateRequestDto(
        @PublicationTitle String tittle,
        @PublicationText String publicationText) {
}
