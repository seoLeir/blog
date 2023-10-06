package io.seoLeir.socialmedia.dto.publication;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PublicationGetResponseDto(UUID id,
                                        String header,
                                        String publicationText,
                                        String publisherUsername,
                                        Instant createdDate,
                                        List<UUID> detachedFiles) {
}
