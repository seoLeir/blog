package io.seoLeir.socialmedia.dto.publication;


import java.util.List;
import java.util.UUID;

public record PublicationCreateRequestDto(String text,
                                          String header,
                                          List<UUID> files) {
}
