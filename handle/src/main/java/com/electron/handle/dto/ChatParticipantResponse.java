package com.electron.handle.dto;

import java.time.Instant;
import java.util.UUID;

public record ChatParticipantResponse(
        UUID id,
        ChatResponse chat,
        UserResponse user,
        Instant joinedAt
) {
}
