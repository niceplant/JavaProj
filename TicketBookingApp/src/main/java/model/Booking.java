package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private int seatId;
    private LocalDateTime bookingTime;

    public Booking() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Movie getMovie() { return movie; }
    public void setMovie(Movie movie) { this.movie = movie; }
    public int getSeatId() { return seatId; }
    public void setSeatId(int seatId) { this.seatId = seatId; }
    public LocalDateTime getBookingTime() { return bookingTime; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }
}
