package io.seoLeir.socialmedia.dto.comment;

import java.time.Instant;
import java.util.UUID;

public record PublicationCommentGetResponseDto(UUID publicationCommentUuid,
                                               UUID userUuid,
                                               UUID publicationUuid,
                                               UUID parentCommentUuid,
                                               String commentMessage,
                                               Instant createdAt,
                                               Long likes,
                                               Long dislikes,
                                               Boolean isLikedByCurrentUser) {
}
