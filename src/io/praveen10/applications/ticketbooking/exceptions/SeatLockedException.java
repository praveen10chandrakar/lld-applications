package io.praveen10.applications.ticketbooking.exceptions;

public class SeatLockedException extends RuntimeException {
    public SeatLockedException(String message) {
        super(message);
    }
}
