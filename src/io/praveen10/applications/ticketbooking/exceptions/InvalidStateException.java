package io.praveen10.applications.ticketbooking.exceptions;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException(String message) {
        super(message);
    }
}
