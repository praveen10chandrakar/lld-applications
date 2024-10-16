package io.praveen10.applications.ticketbooking.services;

import io.praveen10.applications.ticketbooking.model.Seat;
import io.praveen10.applications.ticketbooking.model.Show;
import io.praveen10.applications.ticketbooking.provider.SeatLockProvider;

import java.util.ArrayList;
import java.util.List;

public class SeatAvailabilityService {

    private final BookingService bookingService;
    private final SeatLockProvider seatLockProvider;

    public SeatAvailabilityService(BookingService bookingService, SeatLockProvider seatLockProvider) {
        this.bookingService = bookingService;
        this.seatLockProvider = seatLockProvider;
    }

    public List<Seat> getAvailableSeats(final Show show) {
        final List<Seat> allSeats = show.getScreen().getSeats();
        final List<Seat> unavailableSeats = getUnavailableSeats(show);
        final List<Seat> availableSeats = new ArrayList<>(allSeats);
        allSeats.removeAll(unavailableSeats);
        return allSeats;
    }

    private List<Seat> getUnavailableSeats(final Show show) {
        final List<Seat> unavailableSeats = bookingService.getBookedSeats(show);
        unavailableSeats.addAll(seatLockProvider.getLockedSeats(show));
        return unavailableSeats;
    }
}
