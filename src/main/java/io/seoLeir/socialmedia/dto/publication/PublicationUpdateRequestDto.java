package io.seoLeir.socialmedia.dto.publication;

import io.seoLeir.socialmedia.validation.publication.PublicationText;
import io.seoLeir.socialmedia.validation.publication.PublicationTitle;

public record PublicationUpdateRequestDto(
        @PublicationTitle String tittle,
        @PublicationText String publicationText) {
}
