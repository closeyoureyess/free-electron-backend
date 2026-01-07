package com.electron.handle.repository.adapter;

import com.electron.handle.entity.UserEntity;
import com.electron.handle.repository.jpa.SpringUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
class UserRepositoryAdapterImpl implements UserRepository {

    private final SpringUserRepository repository;

    @Override
    public UserEntity save(UserEntity entity) {
        return repository.save(entity);
    }

    @Override
    public List<UserEntity> saveAllAndFlush(Iterable<UserEntity> entities) {
        return repository.saveAllAndFlush(entities);
    }

    @Override
    public Optional<UserEntity> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
