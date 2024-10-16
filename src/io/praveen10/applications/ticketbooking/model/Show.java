package io.praveen10.applications.ticketbooking.model;

import java.time.LocalDateTime;

public class Show {
    private final String id;
    private final Movie movie;
    private final Screen screen;
    private final LocalDateTime startTime;
    private final Integer durationInMinutes;

    public Show(String id, Movie movie, Screen screen, LocalDateTime startTime, Integer durationInMinutes) {
        this.id = id;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
    }

    public Screen getScreen() {
        return screen;
    }
}
