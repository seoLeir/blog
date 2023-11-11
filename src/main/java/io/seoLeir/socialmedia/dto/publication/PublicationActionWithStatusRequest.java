package io.seoLeir.socialmedia.dto.publication;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PublicationActionWithStatusRequest(
        @NotNull(message = "Status must not be bull") Boolean status,
        @NotBlank(message = "Action must not be blank") String action) {
}
