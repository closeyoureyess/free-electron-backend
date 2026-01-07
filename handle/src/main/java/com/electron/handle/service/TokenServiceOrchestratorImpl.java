package com.electron.handle.service;

import com.electron.handle.dto.*;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.electron.domain.RefreshToken;
import ru.electron.domain.User;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
class TokenServiceOrchestratorImpl implements TokenServiceOrchestrator {

    private final TokenService tokenService;

    private final JwtService jwtService;

    private final UserService userService;

    @Override
    public Optional<TokenPairResponse> verifyIdTokenAndReturnTokenPair(IdTokenRequest idTokenRequest) {
        /// Проверить idtoken, пришедший из Desktop от Google
        GoogleIdToken verifiedGoogleIdToken = tokenService.verifyGoogleIdTokenFromRequest(idTokenRequest);

        /// Найти пользователя по емейл, либо создать нового
        Optional<UserResponse> userResponse = userService.findOrCreateUser(verifiedGoogleIdToken.getPayload());
        return userResponse.map(user -> {
            UUID userId = user.id();
            //Деактивировать все живые токены пользователя, если они есть
            tokenService.lookingActiveTokenAndDeactivate(userId);

            String userName = user.name();
            // Создать JWT - Access токен
            AccessTokenResponse jwtAccessToken = jwtService.generateToken(userName);
            // Сгенерировать рефреш токен
            String refresh = tokenService.generate();
            // Хэшировать созданный рефреш токен
            String refreshHashed = tokenService.sha256WithPepper(refresh);

            String deviceId = idTokenRequest.deviceId();
            // Создать объект рефреш токена, сохранить в базу
            RefreshTokenResponse savedRefreshToken = tokenService.create(userId, deviceId, refreshHashed);

            return new TokenPairResponse(
                    jwtAccessToken.accessToken(),
                    jwtAccessToken.accessExpiresInSec(),
                    savedRefreshToken.refreshToken(),
                    Duration
                            .between(savedRefreshToken.createdAt(), savedRefreshToken.expiresAt())
                            .getSeconds()
            );
        });
    }

    @Override
    public Optional<AccessTokenResponse> refreshAccessToken(IdTokenRequest refreshTokenRequest) {
        String tokenHash = refreshTokenRequest.idTokenRequest();
        // Найти рефреш токен. Если отозван, то ошибка
        RefreshToken refreshToken = tokenService.lookingActiveToken(tokenHash);
        UUID userId = refreshToken.extractUserId();
        Optional<User> optionalUser = userService.findUserById(userId);
        return optionalUser.map(user -> {
            String userName = user.getName().name();
            AccessTokenResponse jwtAccessToken = jwtService.generateToken(userName);
            return jwtAccessToken;
        });
    }
}
