package com.example.week9;

public class Movie {
    private String title;
    private int year;
    private String genre;
    private String poster;

    public Movie(String title, int year, String genre, String poster) {
        setTitle(title);
        setYear(year);
        setGenre(genre);
        setPoster(poster);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            this.title = "Unknown Title";
        } else {
            this.title = title;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year <= 0) {
            this.year = -1;
        } else {
            this.year = year;
        }
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            this.genre = "Unknown Genre";
        } else {
            this.genre = genre;
        }
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        if (poster == null || poster.trim().isEmpty()) {
            this.poster = "default_poster";
        } else {
            this.poster = poster;
        }
    }
}