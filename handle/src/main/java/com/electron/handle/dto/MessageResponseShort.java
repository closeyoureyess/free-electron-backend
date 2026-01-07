package com.electron.handle.dto;

import java.time.Instant;
import java.util.UUID;

public record MessageResponseShort(
        UUID id,
        String content,
        UUID userId,
        Instant createdAt
) {
}
