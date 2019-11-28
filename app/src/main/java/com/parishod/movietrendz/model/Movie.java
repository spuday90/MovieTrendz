package com.parishod.movietrendz.model;

public class Movie {
    private int id;
    private String title;
    private String overview;
    private String poster_path;
    private String vote_average;
    private String release_date;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getRelease_date() {
        return release_date;
    }
}
