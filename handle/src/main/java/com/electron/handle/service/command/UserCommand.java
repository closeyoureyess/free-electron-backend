package com.electron.handle.service.command;

import com.electron.handle.dto.UserResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import ru.electron.domain.User;

import java.util.Optional;

public interface UserCommand {

    User createUser(GoogleIdToken.Payload idTokenPayload);

    Optional<UserResponse> findOrCreateUser(User user);
}
