package io.seoLeir.blog.dto.publication;

import java.util.UUID;

public record PublicationLikeAndDislikeDto(UUID id,
                                           Long likes,
                                           Long dislikes) {
}
