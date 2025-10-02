package model;

import jakarta.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private String seatNumber;
    private boolean isBooked;

    public Seat() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }
}
