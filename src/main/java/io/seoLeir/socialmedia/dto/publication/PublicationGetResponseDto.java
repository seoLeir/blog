package io.seoLeir.socialmedia.dto.publication;

import io.seoLeir.socialmedia.entity.Publication;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PublicationGetResponseDto(UUID id,
                                        String tittle,
                                        String text,
                                        String user,
                                        Instant createdDate,
                                        Integer timeToRead,
                                        Integer likes,
                                        Integer dislikes,
                                        Boolean isBookmarkedByCurrentUser,
                                        Boolean isLikedByCurrentUser,
                                        List<UUID> publicationFiles) {
    public static PublicationGetResponseDto of(Publication publication,
                                               List<UUID> fileList,
                                               Integer likes,
                                               Integer dislikes,
                                               Boolean isBookmarkedByCurrentUser,
                                               Boolean isLikedByCurrentUser){
        return new PublicationGetResponseDto(publication.getId(),
                publication.getTittle(),
                publication.getText(),
                publication.getUser().getUsername(),
                publication.getCreatedDate(),
                publication.getTimeToReadInMinutes(),
                likes,
                dislikes,
                isBookmarkedByCurrentUser,
                isLikedByCurrentUser,
                fileList);
    }
}
