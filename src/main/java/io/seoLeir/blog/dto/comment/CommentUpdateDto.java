package io.seoLeir.blog.dto.comment;

import io.seoLeir.blog.validation.comment.CommentText;

import java.util.UUID;

public record CommentUpdateDto(@org.hibernate.validator.constraints.UUID UUID commentUuid,
                               @CommentText String text) {
}
