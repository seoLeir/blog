package io.seoLeir.socialmedia.dto.comment;

import java.util.UUID;

public record CommentDeleteDto(
        @org.hibernate.validator.constraints.UUID UUID commentUuid) {
}
