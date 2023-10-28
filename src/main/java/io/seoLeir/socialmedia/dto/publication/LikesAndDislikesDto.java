package io.seoLeir.socialmedia.dto.publication;

import java.util.UUID;

public record LikesAndDislikesDto(UUID publicationUuid, Integer likes, Integer dislikes) {
}
