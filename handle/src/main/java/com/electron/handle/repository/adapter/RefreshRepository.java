package com.electron.handle.repository.adapter;

import com.electron.handle.entity.RefreshTokenEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RefreshRepository extends Repository<UUID, RefreshTokenEntity> {

    Optional<RefreshTokenEntity> findByTokenHash(String tokenHash);

    List<RefreshTokenEntity> findAllByUserIdAndRevokedAtNull(UUID userId);
}
