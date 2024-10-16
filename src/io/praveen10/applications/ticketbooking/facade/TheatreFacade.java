package io.praveen10.applications.ticketbooking.facade;

import io.praveen10.applications.ticketbooking.model.Screen;
import io.praveen10.applications.ticketbooking.model.Seat;
import io.praveen10.applications.ticketbooking.model.Theatre;
import io.praveen10.applications.ticketbooking.services.TheatreService;

public class TheatreFacade {

    private final TheatreService theatreService;

    public TheatreFacade(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    public Theatre createTheatre(final String theatreName) {
        return theatreService.createTheatre(theatreName);
    }

    public Screen createScreenInTheatre(final String screenName, final Theatre theatre) {
        return theatreService.createScreenInTheatre(screenName, theatre);
    }

    final Seat createSeatInScreen(final Integer rowNo, final Integer seatNo, final Screen screen) {
        return theatreService.createSeatInScreen(rowNo, seatNo, screen);
    }
}
