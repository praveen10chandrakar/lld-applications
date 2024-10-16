package io.praveen10.applications.ticketbooking.exceptions;

public class ScreenOccupiedException extends RuntimeException {
    public ScreenOccupiedException(String message) {
        super(message);
    }
}
