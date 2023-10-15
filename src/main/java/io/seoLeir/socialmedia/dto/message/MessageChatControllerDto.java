package io.seoLeir.socialmedia.dto.message;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

public record MessageChatControllerDto(UUID id,
                                       UUID from,
                                       UUID to,
                                       Instant sentTime,
                                       String lastMessage,
                                       Boolean isRead) {
}
