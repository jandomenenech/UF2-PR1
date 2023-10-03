package org.example;

import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    private int id;
    private String title;
    private String releaseDate;
    private ArrayList<String> team;
    private double rating;
    private int timesListed;
    private int numberOfReviews;
    private ArrayList<String> genres;
    private String summary;
    private ArrayList<String> reviews;
    private String plays;
    private String playing;
    private String backlogs;
    private String wishlist;

    public Game(int id, String title, String releaseDate, ArrayList<String> team, double rating, int timesListed,
                int numberOfReviews, ArrayList<String> genres, String summary, ArrayList<String> reviews, String plays,
                String playing, String backlogs, String wishlist) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.team = team;
        this.rating = rating;
        this.timesListed = timesListed;
        this.numberOfReviews = numberOfReviews;
        this.genres = genres;
        this.summary = summary;
        this.reviews = reviews;
        this.plays = plays;
        this.playing = playing;
        this.backlogs = backlogs;
        this.wishlist = wishlist;
    }


}
