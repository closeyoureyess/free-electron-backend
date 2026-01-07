package com.electron.handle.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public record AccessTokenResponse(
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        String accessToken,

        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        long accessExpiresInSec
) {
}
