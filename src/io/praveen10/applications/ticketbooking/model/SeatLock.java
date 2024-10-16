package io.praveen10.applications.ticketbooking.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

public class SeatLock {

    private Seat seat;
    private Show show;
    private Integer timeoutInSeconds;
    private LocalDateTime lockTime;
    private String lockedBy;

    public SeatLock(Seat seat, Show show, Integer timeoutInSeconds, LocalDateTime lockTime, String lockedBy) {
        this.seat = seat;
        this.show = show;
        this.timeoutInSeconds = timeoutInSeconds;
        this.lockTime = lockTime;
        this.lockedBy = lockedBy;
    }

    public boolean isLockExpired() {
        final Instant lockInstant = lockTime.toInstant(ZoneOffset.UTC)
                .plus(timeoutInSeconds, ChronoUnit.SECONDS);
        final Instant currentInstant = LocalDateTime.now().toInstant(ZoneOffset.UTC);
        return lockInstant.isBefore(currentInstant);
    }

    public String getLockedBy() {
        return lockedBy;
    }
}
