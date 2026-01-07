package com.electron.handle.dto;

import java.time.Instant;
import java.util.Set;

public record ChatResponse(
        Long id,
        String type,
        Instant createdAt,
        Instant lastMessageAt,
        Set<MessageResponseShort> messages
) {
}
