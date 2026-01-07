package com.electron.handle.repository.adapter;


import com.electron.handle.entity.UserEntity;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends Repository<UUID, UserEntity> {

    Optional<UserEntity> findByEmail(String email);
}
