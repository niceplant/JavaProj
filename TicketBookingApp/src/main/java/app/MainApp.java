package app;

import dao.BookingDAO;
import dao.MovieDAO;
import model.Movie;

import java.sql.*;
import java.util.*;

public class MainApp {

    private static final String URL = "jdbc:mysql://localhost:3306/ticketdb";
    private static final String USER = "ticketuser";
    private static final String PASS = "password123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieDAO movieDAO = new MovieDAO();
        BookingDAO bookingDAO = new BookingDAO();

        int userId = getOrCreateUser(sc); // Ask for name and get userId

        // 1. Show movies
        List<Movie> movies = movieDAO.getAllMovies();
        System.out.println("Available Movies:");
        for (Movie m : movies) {
            System.out.println(m);
        }

        // 2. User picks a movie
        System.out.print("Enter Movie ID to book: ");
        int movieId = sc.nextInt();

        // 3. Show available seats
        List<Integer> bookedSeats = bookingDAO.getBookedSeats(movieId);
        System.out.println("Seats 1-50, X = booked");
        for (int i = 1; i <= 50; i++) {
            if (bookedSeats.contains(i)) System.out.print("X ");
            else System.out.print(i + " ");
            if (i % 10 == 0) System.out.println();
        }

        // 4. User selects seats
        sc.nextLine(); // consume newline
        System.out.print("Enter seat numbers to book (comma separated): ");
        String seatInput = sc.nextLine();
        String[] seatStrings = seatInput.split(",");
        List<Integer> selectedSeats = new ArrayList<>();
        for (String s : seatStrings) {
            selectedSeats.add(Integer.parseInt(s.trim()));
        }

        // 5. Check availability and book
        double pricePerSeat = 0;
        for (Movie m : movies) {
            if (m.getId() == movieId) {
                pricePerSeat = m.getBasePrice();
                break;
            }
        }

        int seatsBooked = 0;
        for (int seat : selectedSeats) {
            if (bookingDAO.isSeatBooked(movieId, seat)) {
                System.out.println("Seat " + seat + " is already booked!");
            } else {
                bookingDAO.bookSeat(userId, movieId, seat);
                seatsBooked++;
                System.out.println("Seat " + seat + " booked successfully!");
            }
        }

        System.out.println("Booking complete! Total price: ₹" + (seatsBooked * pricePerSeat));
    }

    // Helper: get user ID from name or create new user
    private static int getOrCreateUser(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        int userId = -1;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            // Check if user exists
            String checkSql = "SELECT id FROM users WHERE name = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkSql)) {
                ps.setString(1, name);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    userId = rs.getInt("id");
                    System.out.println("Welcome back, " + name + "!");
                    return userId;
                }
            }

            // Insert new user
            String insertSql = "INSERT INTO users (name) VALUES (?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, name);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    userId = rs.getInt(1);
                    System.out.println("User created! Welcome, " + name + "!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }
}
