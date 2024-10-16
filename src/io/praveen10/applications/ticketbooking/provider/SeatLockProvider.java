package io.praveen10.applications.ticketbooking.provider;

import io.praveen10.applications.ticketbooking.exceptions.SeatLockedException;
import io.praveen10.applications.ticketbooking.model.Seat;
import io.praveen10.applications.ticketbooking.model.Show;

import java.util.List;

public interface SeatLockProvider {
    void lockSeats(Show show, List<Seat> seats, String user) throws SeatLockedException;
    void unlockSeats(Show show, List<Seat> seats, String user);
    boolean validateLock(Show show, Seat seat, String user);
    List<Seat> getLockedSeats(Show show);
}
