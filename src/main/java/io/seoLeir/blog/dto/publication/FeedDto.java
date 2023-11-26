package io.seoLeir.blog.dto.publication;

import java.util.UUID;

public interface FeedDto {
    UUID getPublicationUuid();
    Double getScore();
}
