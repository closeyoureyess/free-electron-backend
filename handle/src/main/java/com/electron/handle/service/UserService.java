package com.electron.handle.service;

import com.electron.handle.dto.UserResponse;
import com.electron.handle.entity.UserEntity;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.electron.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService extends UserDetailsService {

    Optional<UserResponse> findOrCreateUser(GoogleIdToken.Payload idTokenPayload);

    Optional<User> findUserById(UUID userId);

    Optional<UserEntity> findUserByIdAndReturnEntity(UUID userId);
}