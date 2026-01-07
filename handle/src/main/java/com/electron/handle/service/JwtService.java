package com.electron.handle.service;

import com.electron.handle.dto.AccessTokenResponse;

public interface JwtService {

    AccessTokenResponse generateToken(String userName);

    String extractEmailUser(String jwt);

    boolean isTokenValid(String jwt);
}
