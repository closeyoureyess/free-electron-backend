package com.electron.handle.service.command;

import com.electron.handle.dto.UserResponse;
import com.electron.handle.entity.UserEntity;
import com.electron.handle.repository.adapter.UserRepository;
import com.electron.handle.service.step.UserStep;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.electron.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.electron.handle.enums.NullEnum.NULL;

@Component
@RequiredArgsConstructor
class UserCommandImpl implements UserCommand {

    private final List<UserStep> list;

    private final UserRepository repository;

    @Override
    public User createUser(GoogleIdToken.Payload idTokenPayload) {
        String email = idTokenPayload.getEmail();
        idTokenPayload.get("given_name");
        idTokenPayload.get("family_name");
        return User.create(UUID.randomUUID(),
                idTokenPayload.get("given_name"),
                idTokenPayload.get("family_name"),
                NULL.nullable(), NULL.nullable(), email, NULL.nullable()
        );
    }

    @Override
    public Optional<UserResponse> findOrCreateUser(User user) {
        String email = user.getEmail().email();
        Optional<UserEntity> optionalUserEntity = repository.findByEmail(email);
        return list.stream()
                .filter(e -> optionalUserEntity.isPresent())
                .map(e -> e.execute(user, optionalUserEntity))
                .findFirst();
    }
}
