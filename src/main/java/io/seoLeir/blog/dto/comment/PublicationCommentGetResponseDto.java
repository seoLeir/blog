package io.seoLeir.blog.dto.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class PublicationCommentGetResponseDto {
    UUID publicationCommentUuid;
    UUID userUuid;
    UUID publicationUuid;
    UUID parentCommentUuid;
    String commentMessage;
    Instant createdAt;
    Long likes;
    Long dislikes;
    Boolean isLikedByCurrentUser;
}
