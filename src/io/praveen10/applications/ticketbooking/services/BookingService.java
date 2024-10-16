package io.praveen10.applications.ticketbooking.services;

import io.praveen10.applications.ticketbooking.exceptions.BadRequestException;
import io.praveen10.applications.ticketbooking.exceptions.InvalidStateException;
import io.praveen10.applications.ticketbooking.exceptions.NotFoundException;
import io.praveen10.applications.ticketbooking.exceptions.SeatLockedException;
import io.praveen10.applications.ticketbooking.model.Booking;
import io.praveen10.applications.ticketbooking.model.Seat;
import io.praveen10.applications.ticketbooking.model.Show;
import io.praveen10.applications.ticketbooking.provider.SeatLockProvider;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingService {
    private final Map<String, Booking> bookings;
    private final SeatLockProvider seatLockProvider;

    public BookingService(SeatLockProvider seatLockProvider) {
        this.bookings = new ConcurrentHashMap<>();
        this.seatLockProvider = seatLockProvider;
    }

    public Booking getBooking(final String bookingId) throws NotFoundException {
        if (!bookings.containsKey(bookingId)) {
            throw new NotFoundException("Booking not found");
        }
        return bookings.get(bookingId);
    }

    public List<Booking> getAllBookings(final Show show) {
        List<Booking> response = new ArrayList<>();
        for (Booking booking: bookings.values()) {
            if (booking.getShow().equals(show)) {
                response.add(booking);
            }
        }
        return response;
    }

    public Booking createBooking(final String user, final Show show, final List<Seat> seats) throws SeatLockedException {
        if(isAnySeatAlreadyBooked(show, seats)) {
            throw new SeatLockedException("Seats are not available.");
        }
        seatLockProvider.lockSeats(show, seats, user);
        final String bookingId = UUID.randomUUID().toString();
        final Booking booking = new Booking(bookingId, show, seats, user);
        bookings.put(bookingId, booking);
        return booking;
    }

    public void confirmBooking(final Booking booking, final String user) throws BadRequestException, InvalidStateException {
        if (!booking.getUser().equals(user)) {
            throw new BadRequestException("User mismatch.");
        }

        for (Seat seat: booking.getSeats()) {
            if(!seatLockProvider.validateLock(booking.getShow(), seat, user)) {
                throw new BadRequestException("Seats already booked.");
            }
        }

        booking.confirmBooking();
    }

    private boolean isAnySeatAlreadyBooked(Show show, List<Seat> seats) {
        final Set<Seat> bookedSeats = new HashSet<>(getBookedSeats(show));
        for (Seat seat: seats) {
            if (bookedSeats.contains(seat)) {
                return true;
            }
        }
        return false;
    }

    public List<Seat> getBookedSeats(final Show show) {
        return getAllBookings(show).stream()
                .filter(Booking::isConfirmed)
                .map(Booking::getSeats)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }


}
