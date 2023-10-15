package io.seoLeir.socialmedia.dto.comment;

import java.time.Instant;
import java.util.UUID;

public record PublicationUserCommentsDto(UUID id,
                                         UUID userUuid,
                                         UUID publicationUuid,
                                         UUID parentCommentUuid,
                                         String commentMessage,
                                         Instant createdDate) {
}
