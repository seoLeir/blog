package io.seoLeir.socialmedia.dto.publication;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@AllArgsConstructor
public class PublicationGetResponseDto {
    UUID id;
    String tittle;
    String text;
    String user;
    Instant createdDate;
    Integer timeToRead;
    Long likes;
    Long dislikes;
    Long bookmarksCount;
    Boolean isBookmarkedByCurrentUser;
    Boolean isLikedByCurrentUser;
    List<UUID> publicationFiles;
}
