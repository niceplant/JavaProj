package dao;

import model.Booking;
import model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByMovie(Movie movie);
    boolean existsByMovieAndSeatId(Movie movie, int seatId);
}
