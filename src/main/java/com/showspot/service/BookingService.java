package com.showspot.service;

import com.showspot.entity.*;
import com.showspot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository, TicketRepository ticketRepository) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.ticketRepository = ticketRepository;
    }

    public Booking bookTickets(User user, Show show, List<String> seatNumbers, double pricePerTicket) {
        // Check seat availability
        if (show.getAvailableSeats() < seatNumbers.size()) {
            throw new IllegalArgumentException("Not enough seats available");
        }
        show.setAvailableSeats(show.getAvailableSeats() - seatNumbers.size());
        showRepository.save(show);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setStatus(Booking.Status.CONFIRMED);
        booking = bookingRepository.save(booking);

        for (String seat : seatNumbers) {
            MovieTicket ticket = new MovieTicket();
            ticket.setBooking(booking);
            ticket.setSeatNumber(seat);
            ticket.setPrice(pricePerTicket);
            ticketRepository.save(ticket);
        }
        return booking;
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getUser().getId().equals(user.getId()))
                .toList();
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
