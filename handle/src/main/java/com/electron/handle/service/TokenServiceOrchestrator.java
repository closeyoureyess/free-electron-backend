package com.electron.handle.service;

import com.electron.handle.dto.AccessTokenResponse;
import com.electron.handle.dto.IdTokenRequest;
import com.electron.handle.dto.TokenPairResponse;

import java.util.Optional;

public interface TokenServiceOrchestrator {

    Optional<TokenPairResponse> verifyIdTokenAndReturnTokenPair(IdTokenRequest idTokenRequest);

    Optional<AccessTokenResponse> refreshAccessToken(IdTokenRequest refreshTokenRequest);
}
