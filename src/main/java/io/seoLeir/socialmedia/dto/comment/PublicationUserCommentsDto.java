package io.seoLeir.socialmedia.dto.comment;

import java.time.Instant;
import java.util.UUID;

public record PublicationUserCommentsDto(UUID id,
                                         UUID userId,
                                         UUID publicationId,
                                         UUID parentCommentId,
                                         String commentMessage,
                                         Instant createdAt) {
}
