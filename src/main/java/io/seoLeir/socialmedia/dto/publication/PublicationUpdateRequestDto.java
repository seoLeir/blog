package io.seoLeir.socialmedia.dto.publication;

import jakarta.validation.constraints.NotBlank;

public record PublicationUpdateRequestDto(@NotBlank String tittle,
                                          @NotBlank String publicationText) {
}
