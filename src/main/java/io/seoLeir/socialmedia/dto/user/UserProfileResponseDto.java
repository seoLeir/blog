package io.seoLeir.socialmedia.dto.user;

import java.time.Instant;

public record UserProfileResponseDto(String username,
                                     String email,
                                     String info,
                                     Instant createdAt,
                                     Long publicationCount,
                                     Long commentCount,
                                     Long bookmarksCount) {
}
