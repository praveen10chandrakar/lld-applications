package io.praveen10.applications.ticketbooking.services;

import io.praveen10.applications.parkinglot.account.model.Account;
import io.praveen10.applications.ticketbooking.exceptions.NotFoundException;
import io.praveen10.applications.ticketbooking.model.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MovieService {

    private final Map<String, Movie> movies;

    public MovieService() {
        this.movies = new HashMap<>();
    }

    public Movie getMovie(String movieName) throws NotFoundException {
        if(!movies.containsKey(movieName)) {
            throw new NotFoundException("Movie not found..");
        }
        return movies.get(movieName);
    }

    public Movie createMovie(String movieName) {
        String movieId = UUID.randomUUID().toString();
        Movie movie = new Movie(movieId, movieName);
        movies.put(movieName, movie);
        return movie;
    }
}
