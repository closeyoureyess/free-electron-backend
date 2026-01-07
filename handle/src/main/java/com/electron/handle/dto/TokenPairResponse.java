package com.electron.handle.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public record TokenPairResponse(
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        String accessToken,

        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        long accessExpiresInSec,

        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        String refreshToken,

        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        long refreshExpiresInSec
) {
}
