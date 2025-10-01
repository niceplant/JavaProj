package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/ticketdb";
    private static final String USER = "ticketuser";
    private static final String PASS = "password123";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Connected to database!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
