package ru.electron.domain;

import ru.electron.domain.valueobject.AccessTokenHash;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AccessToken {

    private AccessTokenHash tokenHash;

    private Instant createAt;

    private Instant expireAt;

    public static Date realTokenExpireMillisToDayTimeLine(Instant realTokenExpireMillis) {
        return Date.from(realTokenExpireMillis);
    }

    public static Instant expirePlusNowMillis(Instant now, Long expireMillis) {
        return now.plusMillis(expireMillis);
    }

    public static Long expireMinutesToMillis(Long expireMinutes) {
        return TimeUnit.MINUTES.toMillis(expireMinutes);
    }

    public static Date generateCreatedTimeFromMillis(Instant createdMillis) {
        return Date.from(createdMillis);
    }

    public static Instant generateCreatedTimeMillis() {
        return Instant.now();
    }

    public static Instant generateCurrentTimeInstant() {
        return Instant.now();
    }

    public Instant extractExpirationTime() {
        return this.expireAt;
    }

    public void fixExpirationTime(Long validTime) {
        this.expireAt = Instant.now().plusMillis(validTime);
    }
}
