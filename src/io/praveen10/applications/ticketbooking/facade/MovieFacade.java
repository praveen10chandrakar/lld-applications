package io.praveen10.applications.ticketbooking.facade;

import io.praveen10.applications.ticketbooking.services.MovieService;

public class MovieFacade {

    private final MovieService movieService;

    public MovieFacade(MovieService movieService) {
        this.movieService = movieService;
    }

    public String createMovie(final String movieName) {
        return movieService.createMovie(movieName).getId();
    }

}
