package com.showspot.controller;

import com.showspot.entity.Booking;
import com.showspot.entity.Show;
import com.showspot.entity.User;
import com.showspot.service.BookingService;
import com.showspot.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
public class BookingController {
    private final BookingService bookingService;
    private final ShowService showService;

    @Autowired
    public BookingController(BookingService bookingService, ShowService showService) {
        this.bookingService = bookingService;
        this.showService = showService;
    }

    @GetMapping("/shows/{id}/book")
    public String selectSeats(@PathVariable Long id, Model model) {
        Show show = showService.getShowById(id).orElse(null);
        model.addAttribute("show", show);
        return "select-seats";
    }

    @PostMapping("/shows/{id}/book")
    public String confirmBooking(@PathVariable Long id, @RequestParam List<String> seats, @RequestParam double price, Principal principal, Model model) {
        // In a real app, get User from principal
        User user = new User() {}; // Placeholder, replace with actual user lookup
        Show show = showService.getShowById(id).orElse(null);
        Booking booking = bookingService.bookTickets(user, show, seats, price);
        model.addAttribute("booking", booking);
        return "booking-confirmation";
    }
}
