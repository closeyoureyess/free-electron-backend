package com.electron.handle.service.command;

import com.electron.handle.dto.IdTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import ru.electron.domain.RefreshToken;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenCommand {

    RefreshToken createToken(UUID userId, String deviceId, Duration refreshTtlDays,
                             String refreshHash);

    GoogleIdTokenVerifier createGoogleIdTokenVerifier(String googleClientId);

    GoogleIdToken verifyGoogleTokenFromIncomeRequest(GoogleIdTokenVerifier verifier, IdTokenRequest idTokenRequest);

    Optional<List<RefreshToken>> lookingActiveTokens(UUID userId);

    List<RefreshToken> deactivateToken(List<RefreshToken> refreshTokens);

    RefreshToken lookingActiveToken(String tokenHash);

    boolean deactivateToken(Optional<RefreshToken> refreshToken);
}
