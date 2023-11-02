package io.seoLeir.socialmedia.dto.user;

import lombok.Builder;

import java.time.Instant;

@Builder
public record UserProfileResponseDto(String username,
                                     String email,
                                     String info,
                                     Instant createdAt,
                                     Long publicationCount,
                                     Long commentCount,
                                     Long bookmarksCount,
                                     Long followers,
                                     Long following,
                                     Boolean isUserFollowed) {
}
