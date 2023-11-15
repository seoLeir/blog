package io.seoLeir.blog.dto.comment;

import io.seoLeir.blog.validation.comment.CommentText;

public record CommentCreateRequestDto(
        @CommentText
        String commentText) {
}
