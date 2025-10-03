# Show Spot - Online Movie Ticket Booking Application

## Features
- User registration/login (with password encryption)
- Browse movies and showtimes
- Seat selection and booking
- Payment processing (Credit Card, UPI)
- Admin dashboard for managing movies, shows, and bookings
- Role-based authentication (Admin/Customer)
- Thymeleaf + Bootstrap frontend

## Prerequisites
- Java 17+
- Maven
- MySQL

## Database Setup
1. Create a MySQL database named `showspot`:
   ```sql
   CREATE DATABASE showspot;
   ```
2. Update `src/main/resources/application.properties` with your MySQL username and password.

## Build & Run
1. Install dependencies and build the project:
   ```bash
   mvn clean install
   ```
2. Run the application:
   ```bash
   mvn spring-boot:run
   ```
3. Access the app at [http://localhost:8080](http://localhost:8080)

## Default Admin Login
- Username: `admin`
- Password: `admin`

## Notes
- Use the Admin dashboard to add movies and shows before booking.
- All pages are styled with Bootstrap and rendered using Thymeleaf.
- Passwords are stored securely using BCrypt.

## Project Structure
- `entity/` - JPA entities (User, Movie, Show, etc.)
- `repository/` - Spring Data JPA repositories
- `service/` - Business logic and booking management
- `controller/` - MVC and REST controllers
- `templates/` - Thymeleaf HTML templates
- `config/` - Security configuration

---

Enjoy booking your movies with Show Spot!
