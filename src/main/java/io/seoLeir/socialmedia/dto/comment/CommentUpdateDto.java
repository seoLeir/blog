package io.seoLeir.socialmedia.dto.comment;

import io.seoLeir.socialmedia.validation.comment.CommentText;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CommentUpdateDto(@org.hibernate.validator.constraints.UUID UUID commentUuid,
                               @CommentText String text) {
}
