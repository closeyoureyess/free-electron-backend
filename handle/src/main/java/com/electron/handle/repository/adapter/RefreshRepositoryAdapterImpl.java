package com.electron.handle.repository.adapter;

import com.electron.handle.entity.RefreshTokenEntity;
import com.electron.handle.repository.jpa.SpringRefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class RefreshRepositoryAdapterImpl implements RefreshRepository {

    private final SpringRefreshRepository repository;

    @Override
    public RefreshTokenEntity save(RefreshTokenEntity entity) {
        return repository.save(entity);
    }

    @Override
    public List<RefreshTokenEntity> saveAllAndFlush(Iterable<RefreshTokenEntity> entities) {
        return repository.saveAllAndFlush(entities);
    }

    @Override
    public Optional<RefreshTokenEntity> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<RefreshTokenEntity> findByTokenHash(String tokenHash) {
        return repository.findByTokenHash(tokenHash);
    }

    @Override
    public List<RefreshTokenEntity> findAllByUserIdAndRevokedAtNull(UUID userId) {
        return repository.findAllByUserIdAndRevokedAtNull(userId);
    }

}
