package io.praveen10.applications.ticketbooking.facade;

import io.praveen10.applications.ticketbooking.exceptions.NotFoundException;
import io.praveen10.applications.ticketbooking.model.Booking;
import io.praveen10.applications.ticketbooking.model.Seat;
import io.praveen10.applications.ticketbooking.model.Show;
import io.praveen10.applications.ticketbooking.services.BookingService;
import io.praveen10.applications.ticketbooking.services.ShowService;
import io.praveen10.applications.ticketbooking.services.TheatreService;

import java.util.List;
import java.util.stream.Collectors;

public class BookingFacade {
    private final ShowService showService;
    private final TheatreService theatreService;
    private final BookingService bookingService;

    public BookingFacade(ShowService showService, TheatreService theatreService, BookingService bookingService) {
        this.showService = showService;
        this.theatreService = theatreService;
        this.bookingService = bookingService;
    }

    public Booking createBooking(final String userId, final String showId, final List<String> seatIds) throws NotFoundException {
        final Show show = showService.getShow(showId);
        final List<Seat> seats = seatIds.stream().map(theatreService::getSeat).collect(Collectors.toList());
        return bookingService.createBooking(userId, show, seats);
    }
}
