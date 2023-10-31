package io.seoLeir.socialmedia.dto.publication;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PublicationGetResponseDto(UUID id,
                                        String tittle,
                                        String text,
                                        String user,
                                        Instant createdDate,
                                        Integer timeToRead,
                                        Long likes,
                                        Long dislikes,
                                        Long bookmarksCount,
                                        Boolean isBookmarkedByCurrentUser,
                                        Boolean isLikedByCurrentUser,
                                        List<UUID> publicationFiles) {
}
