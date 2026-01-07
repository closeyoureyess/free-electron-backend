package com.electron.handle.controller;

import com.electron.handle.dto.AccessTokenResponse;
import com.electron.handle.dto.IdTokenRequest;
import com.electron.handle.dto.TokenPairResponse;
import com.electron.handle.service.TokenServiceOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController("/auth")
@RequiredArgsConstructor
class SecurityController {

    private final TokenServiceOrchestrator tokenServiceOrchestrator;

    @PostMapping("/google")
    ResponseEntity<TokenPairResponse> authGoogle(@RequestBody IdTokenRequest googleTokenRequest) {
        Optional<TokenPairResponse> tokenPairResponse = tokenServiceOrchestrator
                .verifyIdTokenAndReturnTokenPair(googleTokenRequest);
        return tokenPairResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/refresh")
    ResponseEntity<AccessTokenResponse> refresh(@RequestBody IdTokenRequest refreshTokenRequest) {
        Optional<AccessTokenResponse> optionalAccessTokenResponse = tokenServiceOrchestrator
                .refreshAccessToken(refreshTokenRequest);
        return optionalAccessTokenResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }
}
