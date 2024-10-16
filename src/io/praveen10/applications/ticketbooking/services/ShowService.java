package io.praveen10.applications.ticketbooking.services;

import io.praveen10.applications.ticketbooking.exceptions.NotFoundException;
import io.praveen10.applications.ticketbooking.exceptions.ScreenOccupiedException;
import io.praveen10.applications.ticketbooking.model.Movie;
import io.praveen10.applications.ticketbooking.model.Screen;
import io.praveen10.applications.ticketbooking.model.Show;

import java.time.LocalDateTime;
import java.util.*;

public class ShowService {
    final Map<String, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show getShow(String showId) throws NotFoundException {
        if (!shows.containsKey(showId)) {
            throw new NotFoundException("Show not found.");
        }
        return shows.get(showId);
    }

    public Show createShow(final Movie movie, final Screen screen, final LocalDateTime startTime,
                        final int duration) throws ScreenOccupiedException {

        if(!checkIfShowCreationAllowed(screen, startTime, duration)) {
            throw new ScreenOccupiedException("Screen is already occupied");
        }
        String showId = UUID.randomUUID().toString();
        final Show show = new Show(showId, movie, screen, startTime, duration);
        shows.put(showId, show);
        return show;
    }

    private List<Show> getShowsForScreen(final Screen screen) {
        final List<Show> response = new ArrayList<>();
        for (Show show: shows.values()) {
            if (show.getScreen().equals(screen)) {
                response.add(show);
            }
        }
        return response;
    }

    private boolean checkIfShowCreationAllowed(Screen screen, LocalDateTime startTime, int duration) {
        // TODO: Implement this. This method will return whether the screen is free at a particular time for
        // specific duration. This function will be helpful in checking whether the show can be scheduled in that slot
        // or not.
        return true;
    }


}
