package com.electron.handle.service;

import com.electron.handle.dto.IdTokenRequest;
import com.electron.handle.dto.RefreshTokenResponse;
import com.electron.handle.entity.RefreshTokenEntity;
import com.electron.handle.mapper.RefreshTokenMapper;
import com.electron.handle.repository.adapter.RefreshRepository;
import com.electron.handle.service.command.TokenCommand;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.electron.domain.RefreshToken;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.*;

import static com.electron.handle.enums.SecurityEnum.SHA;

@Service
@RequiredArgsConstructor
class TokenServiceImpl implements TokenService {

    @Value("${app.security.refresh.pepper}")
    private final String pepper;

    @Value("${app.security.refresh.expire-ttl-days}")
    private final Long refreshTtlDays;

    @Value(value = "${free-electron-backend.security.google-client-id}")
    private final String googleClientId;

    private final SecureRandom rng;

    private final TokenCommand tokenCommand;

    private final RefreshTokenMapper mapper;

    private final RefreshRepository repository;

    @Override
    public RefreshTokenResponse create(UUID userId, String deviceId, String refreshHash) {
        RefreshToken refreshToken = tokenCommand
                .createToken(userId, deviceId, Duration.ofDays(this.refreshTtlDays), refreshHash);
        RefreshTokenEntity refreshTokenEntity = mapper.toRefreshTokenEntity(refreshToken);
        RefreshTokenEntity savedRefreshTokenEntity = repository.save(refreshTokenEntity);
        return mapper.toRefreshTokenResponse(savedRefreshTokenEntity);
    }

    @Override
    public String generate() {
        byte[] bytes = new byte[32]; // 256 бит
        rng.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    @Override
    public String sha256WithPepper(String token) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA.getValue());
            byte[] hash = md.digest((token + this.pepper).getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public GoogleIdToken verifyGoogleIdTokenFromRequest(IdTokenRequest idTokenRequest) {
        GoogleIdTokenVerifier verifier = tokenCommand.createGoogleIdTokenVerifier(googleClientId);
        return tokenCommand
                .verifyGoogleTokenFromIncomeRequest(verifier, idTokenRequest);
    }

    @Override
    public boolean lookingActiveTokenAndDeactivate(UUID userId) {
        Optional<List<RefreshToken>> optionalList = tokenCommand.lookingActiveTokens(userId);
        List<RefreshToken> list = optionalList.map(tokenCommand::deactivateToken).orElse(List.of());
        return !list.isEmpty();
    }

    @Override
    public RefreshToken lookingActiveToken(String tokenHash) {
        return tokenCommand.lookingActiveToken(tokenHash);
    }
}
