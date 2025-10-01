-- =====================================================
-- TicketBookingApp Database Schema (empty, no data)
-- =====================================================

-- 1. Create database
CREATE DATABASE IF NOT EXISTS ticketdb;

-- 2. Create user (optional, for devs)
CREATE USER IF NOT EXISTS 'ticketuser'@'localhost' IDENTIFIED BY 'password123';
GRANT ALL PRIVILEGES ON ticketdb.* TO 'ticketuser'@'localhost';
FLUSH PRIVILEGES;

-- 3. Use the database
USE ticketdb;

-- 4. Create tables

-- Movies table
CREATE TABLE IF NOT EXISTS movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    genre VARCHAR(50),
    base_price DECIMAL(10,2),
    duration INT
);

-- Seats table
CREATE TABLE IF NOT EXISTS seats (
    id INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT,
    seat_number VARCHAR(10),
    is_booked BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (movie_id) REFERENCES movies(id)
);

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE
);

-- Bookings table
CREATE TABLE IF NOT EXISTS bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    movie_id INT,
    seat_id INT,
    booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (movie_id) REFERENCES movies(id),
    FOREIGN KEY (seat_id) REFERENCES seats(id)
);
