package io.praveen10.applications.ticketbooking.facade;

import io.praveen10.applications.ticketbooking.exceptions.NotFoundException;
import io.praveen10.applications.ticketbooking.exceptions.ScreenOccupiedException;
import io.praveen10.applications.ticketbooking.model.Movie;
import io.praveen10.applications.ticketbooking.model.Screen;
import io.praveen10.applications.ticketbooking.model.Seat;
import io.praveen10.applications.ticketbooking.model.Show;
import io.praveen10.applications.ticketbooking.services.MovieService;
import io.praveen10.applications.ticketbooking.services.SeatAvailabilityService;
import io.praveen10.applications.ticketbooking.services.ShowService;
import io.praveen10.applications.ticketbooking.services.TheatreService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ShowFacade {

    private final TheatreService theatreService;

    private final MovieService movieService;

    private final ShowService showService;
    private final SeatAvailabilityService seatAvailabilityService;

    public ShowFacade(TheatreService theatreService, MovieService movieService, ShowService showService, SeatAvailabilityService seatAvailabilityService) {
        this.theatreService = theatreService;
        this.movieService = movieService;
        this.showService = showService;
        this.seatAvailabilityService = seatAvailabilityService;
    }

    public Show createShow(final String movieId, final String screenId, final LocalDateTime startTime,
                           final Integer durationInSeconds) throws ScreenOccupiedException, NotFoundException {
        final Screen screen = theatreService.getScreen(screenId);
        final Movie movie = movieService.getMovie(movieId);

        return showService.createShow(movie, screen, startTime, durationInSeconds);
    }

    public List<String> getAvailableSeats(final String showId) throws NotFoundException {
        final Show show = showService.getShow(showId);
        final List<Seat> availableSeats = seatAvailabilityService.getAvailableSeats(show);
        return availableSeats.stream().map(Seat::getId).collect(Collectors.toList());
    }
}
