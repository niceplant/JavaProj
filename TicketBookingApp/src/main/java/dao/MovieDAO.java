package dao;

import model.Movie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/ticketdb";
    private static final String USER = "ticketuser";
    private static final String PASS = "password123";

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT * FROM movies";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
            	Movie movie = new Movie(
            		    rs.getInt("id"),
            		    rs.getString("title"),
            		    rs.getString("genre"),
            		    rs.getInt("duration"),
            		    rs.getDouble("base_price")
            		);
                movies.add(movie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
