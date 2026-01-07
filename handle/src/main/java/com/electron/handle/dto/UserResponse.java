package com.electron.handle.dto;

import java.util.UUID;

public record UserResponse(
        UUID id,
        String name,
        String surname,
        String bio,
        String nickname,
        String email,
        String numberPhone
) {
}
