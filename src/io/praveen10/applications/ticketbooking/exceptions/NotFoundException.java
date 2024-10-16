package io.praveen10.applications.ticketbooking.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
