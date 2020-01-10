package com.bw.movie.bean.eventbean;

public class EventId {
    int movieId;
    public EventId(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
