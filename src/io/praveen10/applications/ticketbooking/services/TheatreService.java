package io.praveen10.applications.ticketbooking.services;

import io.praveen10.applications.ticketbooking.exceptions.NotFoundException;
import io.praveen10.applications.ticketbooking.model.Screen;
import io.praveen10.applications.ticketbooking.model.Seat;
import io.praveen10.applications.ticketbooking.model.Theatre;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TheatreService {

    private final Map<String, Theatre> theatres;
    private final Map<String, Screen> screens;
    private final Map<String, Seat> seats;

    public TheatreService() {
        theatres = new HashMap<>();
        screens = new HashMap<>();
        seats = new HashMap<>();
    }

    public Seat getSeat(final String seatId) throws NotFoundException {
        if (!seats.containsKey(seatId)) {
            throw new NotFoundException("Seat not found.");
        }
        return seats.get(seatId);
    }

    public Theatre getTheatre(final String theatreId) throws NotFoundException {
        if (!theatres.containsKey(theatreId)) {
            throw new NotFoundException("Seat not found.");
        }
        return theatres.get(theatreId);
    }

    public Screen getScreen(final String screenId) throws NotFoundException {
        if (!screens.containsKey(screenId)) {
            throw new NotFoundException("Seat not found.");
        }
        return screens.get(screenId);
    }

    public Theatre createTheatre(final String theatreName) {
        String theatreId = UUID.randomUUID().toString();
        Theatre theatre = new Theatre(theatreId, theatreName);
        theatres.put(theatreId, theatre);
        return theatre;
    }

    public Screen createScreenInTheatre(final String screenName, final Theatre theatre) {
        Screen screen = createScreen(screenName, theatre);
        theatre.addScreen(screen);
        return screen;
    }

    private Screen createScreen(final String screenName, final Theatre theatre) {
        String screenId = UUID.randomUUID().toString();
        Screen screen = new Screen(screenId, screenName, theatre);
        screens.put(screenId, screen);
        return screen;
    }

    public Seat createSeatInScreen(Integer rowNo, Integer seatNo, final Screen screen) {
        String seatId = UUID.randomUUID().toString();
        Seat seat = new Seat(seatId, rowNo, seatNo);
        seats.put(seatId, seat);
        screen.addSeat(seat);
        return seat;
    }
}
