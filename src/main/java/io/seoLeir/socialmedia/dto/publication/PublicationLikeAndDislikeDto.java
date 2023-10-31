package io.seoLeir.socialmedia.dto.publication;

import java.util.UUID;

public record PublicationLikeAndDislikeDto(UUID id,
                                           Long likes,
                                           Long dislikes) {
}
