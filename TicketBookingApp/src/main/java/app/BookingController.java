package app;

import model.*;
import dao.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingRepository bookingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public BookingController(BookingRepository bookingRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/seats")
    public List<Integer> getBookedSeats(@RequestParam int movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        return bookingRepository.findByMovie(movie)
                .stream()
                .map(Booking::getSeatId)
                .collect(Collectors.toList());
    }

    @PostMapping
    public String bookSeats(@RequestParam int userId, @RequestParam int movieId, @RequestBody List<Integer> seatIds) {
        User user = userRepository.findById(userId).orElseThrow();
        Movie movie = movieRepository.findById(movieId).orElseThrow();
        int booked = 0;
        for (int seatId : seatIds) {
            if (!bookingRepository.existsByMovieAndSeatId(movie, seatId)) {
                Booking booking = new Booking();
                booking.setUser(user);
                booking.setMovie(movie);
                booking.setSeatId(seatId);
                bookingRepository.save(booking);
                booked++;
            }
        }
        return "Booked " + booked + " seats.";
    }
}
