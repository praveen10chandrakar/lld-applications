package io.praveen10.applications.ticketbooking.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Screen {
    private final String id;
    private final String name;
    private final Theatre theatre;

    private final List<Seat> seats;

    public Screen(String id, String name, Theatre theatre) {
        this.id = id;
        this.name = name;
        this.theatre = theatre;
        seats = new ArrayList<>();
    }

    public void addSeat(Seat seat) {
        this.seats.add(seat);
    }

    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }
}
