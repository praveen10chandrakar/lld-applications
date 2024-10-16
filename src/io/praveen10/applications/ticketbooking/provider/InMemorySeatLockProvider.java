package io.praveen10.applications.ticketbooking.provider;

import io.praveen10.applications.ticketbooking.exceptions.SeatLockedException;
import io.praveen10.applications.ticketbooking.model.Seat;
import io.praveen10.applications.ticketbooking.model.SeatLock;
import io.praveen10.applications.ticketbooking.model.Show;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorySeatLockProvider implements SeatLockProvider {

    private final Integer lockTimeout;
    private final Map<Show, Map<Seat, SeatLock>> locks;

    public InMemorySeatLockProvider(final Integer lockTimeout) {
        this.lockTimeout = lockTimeout;
        locks = new ConcurrentHashMap<>();
    }

    @Override
    public synchronized void lockSeats(Show show, List<Seat> seats, String user) throws SeatLockedException {
        for (Seat seat: seats) {
            if(isSeatLocked(show, seat)) {
                throw new SeatLockedException("Seat is locked.");
            }
        }

        for (Seat seat: seats) {
            lockSeat(show, seat, user, lockTimeout);
        }
    }

    private void lockSeat(Show show, Seat seat, String user, Integer lockTimeout) {
        if(!locks.containsKey(show)) {
            locks.put(show, new HashMap<>());
        }

        final SeatLock seatLock = new SeatLock(seat, show, lockTimeout, LocalDateTime.now(), user);
        locks.get(show).put(seat, seatLock);
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seats, String user) {
        for (Seat seat: seats) {
            if (validateLock(show, seat, user)) {
                unlockSeat(show, seat);
            }
        }
    }

    private void unlockSeat(Show show, Seat seat) {
        if (!locks.containsKey(show))
            return;
        locks.get(show).remove(seat);
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) {
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        if (!locks.containsKey(show)) {
            return Collections.emptyList();
        }

        final List<Seat> lockedSeats = new ArrayList<>();
        for (Seat seat: locks.get(show).keySet()) {
            if(isSeatLocked(show, seat)) {
                lockedSeats.add(seat);
            }
        }
        return lockedSeats;
    }

    private boolean isSeatLocked(Show show, Seat seat) {
        return locks.containsKey(show) && locks.get(show).containsKey(seat) &&
                !locks.get(show).get(seat).isLockExpired();
    }
}
