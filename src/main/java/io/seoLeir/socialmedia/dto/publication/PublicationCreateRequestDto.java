package io.seoLeir.socialmedia.dto.publication;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record PublicationCreateRequestDto( @NotBlank
                                           String text,
                                           @NotNull
                                           @Size(max = 128)
                                           String header,
                                           @NotNull
                                           List<UUID> files) {
}
