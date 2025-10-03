package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/ticketdb";
    private static final String USER = "ticketuser";
    private static final String PASS = "password123";

    // Check if a seat is booked
    public boolean isSeatBooked(int movieId, int seatId) {
        String sql = "SELECT * FROM bookings WHERE movie_id = ? AND seat_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, movieId);
            pstmt.setInt(2, seatId);

            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // true if seat is already booked

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Book a single seat
    public void bookSeat(int userId, int movieId, int seatId) {
        String sql = "INSERT INTO bookings (user_id, movie_id, seat_id) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.setInt(2, movieId);
            pstmt.setInt(3, seatId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all booked seats for a movie
    public List<Integer> getBookedSeats(int movieId) {
        List<Integer> booked = new ArrayList<>();
        String sql = "SELECT seat_id FROM bookings WHERE movie_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, movieId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                booked.add(rs.getInt("seat_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booked;
    }
}
