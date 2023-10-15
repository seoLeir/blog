package io.seoLeir.socialmedia.dto.user;

import java.util.List;
import java.util.UUID;

public record UserGetAllPublicationResponseDto(List<UUID> publicationsUuidList) {
}
