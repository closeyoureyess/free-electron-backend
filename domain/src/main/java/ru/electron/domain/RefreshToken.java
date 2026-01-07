package ru.electron.domain;

import lombok.Builder;
import lombok.Data;
import ru.electron.domain.valueobject.RefreshDeviceId;
import ru.electron.domain.valueobject.RefreshTokenHash;
import ru.electron.domain.valueobject.RefreshTokenId;
import ru.electron.domain.valueobject.RefreshUserId;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class RefreshToken {

    private RefreshTokenId id;

    private RefreshUserId userId;

    private RefreshTokenHash tokenHash;

    private Instant createdAt;

    private Instant expiresAt;

    private Instant revokedAt;

    private Instant lastUsedAt;

    private RefreshDeviceId deviceId;

    public static RefreshToken create(UUID id,
                                      UUID userId,
                                      String tokenHash,
                                      Instant createdAt,
                                      Instant expiresAt,
                                      Instant revokedAt,
                                      Instant lastUsedAt,
                                      String deviceId
    ) {
        RefreshToken.RefreshTokenBuilder refreshTokenBuilder = RefreshToken.builder();
        if (id != null) refreshTokenBuilder = refreshTokenBuilder.id(new RefreshTokenId(id));
        if (userId != null) refreshTokenBuilder = refreshTokenBuilder.userId(new RefreshUserId(userId));
        if (tokenHash != null) refreshTokenBuilder = refreshTokenBuilder.tokenHash(new RefreshTokenHash(tokenHash));
        if (deviceId != null) refreshTokenBuilder = refreshTokenBuilder.deviceId(new RefreshDeviceId(deviceId));
        return refreshTokenBuilder
                .createdAt(createdAt)
                .expiresAt(expiresAt)
                .revokedAt(revokedAt)
                .lastUsedAt(lastUsedAt)
                .build();
    }

    public static RefreshToken create(UUID userId,
                                      String tokenHash,
                                      Instant expiresAt,
                                      String deviceId
    ) {
        RefreshToken.RefreshTokenBuilder refreshTokenBuilder = RefreshToken.builder();
        if (userId != null) refreshTokenBuilder = refreshTokenBuilder.userId(new RefreshUserId(userId));
        if (tokenHash != null) refreshTokenBuilder = refreshTokenBuilder.tokenHash(new RefreshTokenHash(tokenHash));
        if (deviceId != null) refreshTokenBuilder = refreshTokenBuilder.deviceId(new RefreshDeviceId(deviceId));
        return refreshTokenBuilder
                .expiresAt(expiresAt)
                .build();
    }

    public void fixCreatedTime() {
        this.createdAt = Instant.now();
    }

    public void fixLastUsedTime() {
        this.lastUsedAt = Instant.now();
    }

    public void fixExpiredTime(Instant time) {
        this.expiresAt = time;
    }

    public UUID extractUserId() {
        return this.userId != null ? this.userId.userId() : null;
    }
}
