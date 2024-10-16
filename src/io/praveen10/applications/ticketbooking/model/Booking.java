package io.praveen10.applications.ticketbooking.model;

import io.praveen10.applications.ticketbooking.enums.BookingStatus;
import io.praveen10.applications.ticketbooking.exceptions.InvalidStateException;

import java.util.Collections;
import java.util.List;

public class Booking {
    private final String id;
    private final Show show;
    private final List<Seat> seats;
    private final String user;
    private BookingStatus bookingStatus;

    public Booking(String id, Show show, List<Seat> seats, String user) {
        this.id = id;
        this.show = show;
        this.seats = seats;
        this.user = user;
        bookingStatus = BookingStatus.CREATED;
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() throws InvalidStateException {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new InvalidStateException("Invalid Booking state.");
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expireBooking() throws InvalidStateException {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new InvalidStateException("Invalid Booking state.");
        }
        this.bookingStatus = BookingStatus.EXPIRED;
    }

    public String getId() {
        return id;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
       return Collections.unmodifiableList(seats);
    }

    public String getUser() {
        return user;
    }
}
