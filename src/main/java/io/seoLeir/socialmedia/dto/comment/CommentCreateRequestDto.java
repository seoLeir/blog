package io.seoLeir.socialmedia.dto.comment;

import io.seoLeir.socialmedia.validation.comment.CommentText;

public record CommentCreateRequestDto(
        @CommentText
        String commentText) {
}
