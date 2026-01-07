package com.electron.handle.service.command;

import com.electron.handle.dto.IdTokenRequest;
import com.electron.handle.entity.RefreshTokenEntity;
import com.electron.handle.mapper.RefreshTokenMapper;
import com.electron.handle.repository.adapter.RefreshRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import ru.electron.domain.RefreshToken;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
class TokenCommandImpl implements TokenCommand {

    private final RefreshRepository repository;

    private final RefreshTokenMapper refreshTokenMapper;

    @Override
    public RefreshToken createToken(UUID userId, String deviceId, Duration refreshTtlDays,
                                    String refreshHash) {
        RefreshToken refreshToken = RefreshToken.create(userId, deviceId,
                Instant.now().plus(refreshTtlDays), refreshHash);
        refreshToken.fixCreatedTime();
        refreshToken.fixLastUsedTime();
        return refreshToken;
    }

    @Override
    public GoogleIdTokenVerifier createGoogleIdTokenVerifier(String googleClientId) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), GsonFactory.getDefaultInstance()
        )
                .setAudience(List.of(googleClientId)) // тот же clientId, что в desktop приложении
                .build();
        return verifier;
    }

    @Override
    public GoogleIdToken verifyGoogleTokenFromIncomeRequest(GoogleIdTokenVerifier verifier,
                                                            IdTokenRequest idTokenRequest) {
        try {
            String idTokenRequestString = idTokenRequest.idTokenRequest();
            GoogleIdToken googleIdToken = verifier.verify(idTokenRequestString);
            if (googleIdToken == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
            return googleIdToken;
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<List<RefreshToken>> lookingActiveTokens(UUID userId) {
        List<RefreshTokenEntity> list = repository.findAllByUserIdAndRevokedAtNull(userId);
        if (listNotNullAndNotEmpty(list)) {
            return Optional.of(refreshTokenMapper.toListRefreshToken(list));
        }
        return Optional.empty();
    }

    @Override
    public List<RefreshToken> deactivateToken(List<RefreshToken> refreshTokens) {
        return Optional.ofNullable(refreshTokens)
                .map(tokens -> {
                            tokens.forEach(token -> {
                                token.fixExpiredTime(Instant.now());
                            });
                            List<RefreshTokenEntity> refreshTokenEntityList =
                                    refreshTokenMapper.toListRefreshTokenEntity(tokens);
                            List<RefreshTokenEntity> entityList =
                                    repository.saveAllAndFlush(refreshTokenEntityList);
                            return refreshTokenMapper.toListRefreshToken(entityList);
                        }
                ).orElse(List.of());
    }

    @Override
    public RefreshToken lookingActiveToken(String tokenHash) {
        Optional<RefreshTokenEntity> refreshTokenEntity = repository.findByTokenHash(tokenHash);
        return refreshTokenEntity
                .filter(token -> token.getRevokedAt() != null)
                .map(refreshTokenMapper::toRefreshToken)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    }

    @Override
    public boolean deactivateToken(Optional<RefreshToken> refreshToken) {
        return false;
    }

    protected boolean listNotNullAndNotEmpty(List<?> list) {
        return list != null && !list.isEmpty();
    }
}
