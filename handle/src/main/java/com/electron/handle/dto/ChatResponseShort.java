package com.electron.handle.dto;

import java.time.Instant;
import java.util.UUID;

public record ChatResponseShort(
        Long id,
        String type,
        Instant createdAt,
        Instant lastMessageAt
) {
}
