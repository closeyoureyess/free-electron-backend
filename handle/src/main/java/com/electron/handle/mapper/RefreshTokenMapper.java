package com.electron.handle.mapper;

import com.electron.handle.dto.RefreshTokenResponse;
import com.electron.handle.entity.RefreshTokenEntity;
import org.mapstruct.*;
import ru.electron.domain.RefreshToken;
import ru.electron.domain.valueobject.RefreshDeviceId;
import ru.electron.domain.valueobject.RefreshTokenHash;
import ru.electron.domain.valueobject.RefreshTokenId;
import ru.electron.domain.valueobject.RefreshUserId;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface RefreshTokenMapper {

    @IterableMapping(qualifiedByName = "refreshTokenToRefreshTokenEntity")
    List<RefreshTokenEntity> toListRefreshTokenEntity(List<RefreshToken> refreshTokens);

    @IterableMapping(qualifiedByName = "refreshTokenEntityToRefreshToken")
    List<RefreshToken> toListRefreshToken(List<RefreshTokenEntity> refreshTokenEntities);

    @Named("refreshTokenEntityToRefreshToken")
    @Mapping(source = "id", target = "id", qualifiedByName = "toRefreshTokenId")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "toRefreshUserId")
    @Mapping(source = "tokenHash", target = "tokenHash", qualifiedByName = "toRefreshTokenHash")
    @Mapping(source = "deviceId", target = "deviceId", qualifiedByName = "toRefreshDeviceId")
    RefreshToken toRefreshToken(RefreshTokenEntity refreshTokenEntity);

    @Named("toRefreshTokenId")
    default RefreshTokenId toRefreshTokenId(UUID id) {
        return id != null ? new RefreshTokenId(id) : null;
    }

    @Named("toRefreshUserId")
    default RefreshUserId toRefreshUserId(UUID userId) {
        return userId != null ? new RefreshUserId(userId) : null;
    }

    @Named("toRefreshTokenHash")
    default RefreshTokenHash toRefreshTokenHash(String tokenHash) {
        return tokenHash != null ? new RefreshTokenHash(tokenHash) : null;
    }

    @Named("toRefreshDeviceId")
    default RefreshDeviceId toRefreshDeviceId(String deviceId) {
        return deviceId != null ? new RefreshDeviceId(deviceId) : null;
    }

    @Named("refreshTokenEntityToRefreshTokenResponse")
    RefreshTokenResponse toRefreshTokenResponse(RefreshTokenEntity refreshTokenEntity);

    @Named("refreshTokenToRefreshTokenEntity")
    @Mapping(source = "id", target = "id", qualifiedByName = "toId")
    @Mapping(source = "userId", target = "userId", qualifiedByName = "toUserId")
    @Mapping(source = "tokenHash", target = "tokenHash", qualifiedByName = "toTokenHash")
    @Mapping(source = "deviceId", target = "deviceId", qualifiedByName = "toDeviceId")
    RefreshTokenEntity toRefreshTokenEntity(RefreshToken refreshToken);

    @Named("toId")
    default UUID toId(RefreshTokenId refreshTokenId) {
        return refreshTokenId != null ? refreshTokenId.id() : null;
    }

    @Named("toUserId")
    default UUID toUserId(RefreshUserId userId) {
        return userId != null ? userId.userId() : null;
    }

    @Named("toTokenHash")
    default String toTokenHash(RefreshTokenHash tokenHash) {
        return tokenHash != null ? tokenHash.tokenHash() : null;
    }

    @Named("toDeviceId")
    default String toDeviceId(RefreshDeviceId deviceId) {
        return deviceId != null ? deviceId.deviceId() : null;
    }
}
