package com.electron.handle.service;

import com.electron.handle.dto.AccessTokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.electron.domain.AccessToken;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {

    @Value("${app.security.jwt.secret}")
    private final String SECRET_KEY;

    @Value("${app.security.jwt.expire-ttl-minutes}")
    private final Long expireTtlMinutes;

    @Override
    public AccessTokenResponse generateToken(String userName) {
        Long expireMillis = AccessToken.expireMinutesToMillis(expireTtlMinutes);
        Instant nowForPlusByExpireMillis = AccessToken.generateCurrentTimeInstant();
        Instant expirePlusNowMillis = AccessToken.expirePlusNowMillis(nowForPlusByExpireMillis, expireMillis);
        Date dateExpire = AccessToken.realTokenExpireMillisToDayTimeLine(expirePlusNowMillis);

        Instant createdMillis = AccessToken.generateCreatedTimeMillis();
        Date createdTimeLine = AccessToken.generateCreatedTimeFromMillis(createdMillis);

        String jwtAccessToken = Jwts.builder()
                .subject(userName)
                .issuedAt(createdTimeLine)
                .expiration(dateExpire)
                .signWith(this.generateKey())
                .compact();


        return new AccessTokenResponse(jwtAccessToken,
                Duration.between(Instant.now(), expirePlusNowMillis).getSeconds());
    }

    protected SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    @Override
    public String extractEmailUser(String jwt) {
        Claims claims = this.getClaims(jwt);
        return claims.getSubject();
    }

    @Override
    public boolean isTokenValid(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }

    protected Claims getClaims(String jwt) {
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }
}