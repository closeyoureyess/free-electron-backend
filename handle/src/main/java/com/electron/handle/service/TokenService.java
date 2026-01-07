package com.electron.handle.service;

import com.electron.handle.dto.IdTokenRequest;
import com.electron.handle.dto.RefreshTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import ru.electron.domain.RefreshToken;

import java.util.UUID;

public interface TokenService {

    RefreshTokenResponse create(UUID userId, String deviceId, String refreshHash);

    /**
     * Генерация рефреш токена
     *
     * @return String рефреш токен
     */
    String generate();

    /**
     * Функциональность для хэширования рефреш токена
     *
     * @param token  Токен для хэширования
     * @param pepper соль
     * @return захэшированный токен
     */
    String sha256WithPepper(String token);

    GoogleIdToken verifyGoogleIdTokenFromRequest(IdTokenRequest idTokenRequest);

    boolean lookingActiveTokenAndDeactivate(UUID userId);

    RefreshToken lookingActiveToken(String tokenHash);
}
