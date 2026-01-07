package com.electron.handle.service.step;

import com.electron.handle.dto.UserResponse;
import com.electron.handle.entity.UserEntity;
import ru.electron.domain.User;

import java.util.Optional;

public interface UserStep {

    boolean isSupported(boolean supportedStep);

    UserResponse execute(User user, Optional<UserEntity> userEntity);
}
