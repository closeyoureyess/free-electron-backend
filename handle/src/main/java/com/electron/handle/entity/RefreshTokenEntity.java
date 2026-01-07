package com.electron.handle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "refresh_token")
public class RefreshTokenEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(nullable = false, name = "user_id")
    private UUID userId;

    @Column(nullable = false, unique = true, length = 64, name = "token_hash")
    private String tokenHash;

    @Column(nullable = false, name = "created_at")
    private Instant createdAt;

    @Column(nullable = false, name = "expires_at")
    private Instant expiresAt;

    @Column(name = "revoked_at")
    private Instant revokedAt;

    @Column(nullable = false, name = "last_user_at")
    private Instant lastUsedAt;

    @Column(name = "device_id")
    private String deviceId;
}
