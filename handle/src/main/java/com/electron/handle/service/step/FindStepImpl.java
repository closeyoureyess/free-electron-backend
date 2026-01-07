package com.electron.handle.service.step;

import com.electron.handle.dto.UserResponse;
import com.electron.handle.entity.UserEntity;
import com.electron.handle.mapper.UserMapper;
import com.electron.handle.repository.adapter.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.electron.domain.User;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class FindStepImpl implements UserStep {

    private final UserRepository repository;

    private final UserMapper mapper;

    @Override
    public boolean isSupported(boolean supportedStep) {
        return supportedStep;
    }

    @Override
    public UserResponse execute(User user, Optional<UserEntity> userEntity) {
        UserEntity userEntityFromOptional = userEntity.orElseThrow(); /// Если этот шаг, ошибки точно не будет
        return mapper.toUserResponse(userEntityFromOptional);
    }
}
