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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArrayList<String> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<String> team) {
        this.team = team;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTimesListed() {
        return timesListed;
    }

    public void setTimesListed(int timesListed) {
        this.timesListed = timesListed;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public ArrayList<String> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<String> reviews) {
        this.reviews = reviews;
    }

    public String getPlays() {
        return plays;
    }

    public void setPlays(String plays) {
        this.plays = plays;
    }

    public String getPlaying() {
        return playing;
    }

    public void setPlaying(String playing) {
        this.playing = playing;
    }

    public String getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(String backlogs) {
        this.backlogs = backlogs;
    }

    public String getWishlist() {
        return wishlist;
    }

    public void setWishlist(String wishlist) {
        this.wishlist = wishlist;
    }

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
