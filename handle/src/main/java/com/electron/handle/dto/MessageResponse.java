package com.electron.handle.dto;

import java.time.Instant;
import java.util.UUID;

public record MessageResponse(
        UUID id,
        String content,
        UUID userId,
        Instant createdAt,
        ChatResponseShort chat
) {
}
