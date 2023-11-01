package io.seoLeir.socialmedia.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CommentUpdateDto(@NotNull UUID commentUuid,
                               @NotBlank String text) {
}
