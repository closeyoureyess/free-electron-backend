package com.electron.handle.dto;

import java.time.Instant;

public record RefreshTokenResponse(
        String refreshToken,
        Instant createdAt,
        Instant expiresAt
) {
}
