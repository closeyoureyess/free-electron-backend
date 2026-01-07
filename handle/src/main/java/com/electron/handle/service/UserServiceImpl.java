package com.electron.handle.service;

import com.electron.handle.dto.UserResponse;
import com.electron.handle.entity.UserEntity;
import com.electron.handle.mapper.UserMapper;
import com.electron.handle.repository.adapter.UserRepository;
import com.electron.handle.service.command.UserCommand;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserCommand userCommand;

    private final UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UUID userIdUUID = UUID.fromString(userId);
        Optional<UserEntity> mapUserEntity = userRepository.findById(userIdUUID);
        return mapUserEntity.map(e -> User.builder().username(userId).build())
                .orElseThrow();
    }

    @Override
    public Optional<UserResponse> findOrCreateUser(GoogleIdToken.Payload idTokenPayload) {
        ru.electron.domain.User user = userCommand.createUser(idTokenPayload);
        return userCommand.findOrCreateUser(user);
    }

    @Override
    public Optional<ru.electron.domain.User> findUserById(UUID userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        return userEntity.map(mapper::toUser);
    }

    @Override
    public Optional<UserEntity> findUserByIdAndReturnEntity(UUID userId) {
        return userRepository.findById(userId);
    }
}