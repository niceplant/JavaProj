package model;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private int duration;
    private double basePrice;

    public Movie(int id, String title, String genre, int duration, double basePrice) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.basePrice = basePrice;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getDuration() { return duration; }
    public double getBasePrice() { return basePrice; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + genre + " | " + duration + " mins | ₹" + basePrice;
    }
}

