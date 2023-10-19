package io.seoLeir.socialmedia.dto.comment;

import java.time.Instant;
import java.util.UUID;

public record PublicationUserCommentsWithLikesDto(UUID id,
                                                  UUID userId,
                                                  UUID parentCommentId,
                                                  String commentMessage,
                                                  Instant createdAt,
                                                  Integer likes) {
}
