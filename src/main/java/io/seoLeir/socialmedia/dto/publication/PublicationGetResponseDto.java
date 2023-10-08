package io.seoLeir.socialmedia.dto.publication;

import io.seoLeir.socialmedia.entity.Publication;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PublicationGetResponseDto(UUID id,
                                        String header,
                                        String publicationText,
                                        String publisherUsername,
                                        Instant createdDate,
                                        List<UUID> detachedFiles) {
    public static PublicationGetResponseDto of(Publication publication, List<UUID> fileList){
        return new PublicationGetResponseDto(publication.getId(),
                publication.getTittle(),
                publication.getText(),
                publication.getUser().getUsername(),
                publication.getCreatedDate(), fileList);
    }
}
