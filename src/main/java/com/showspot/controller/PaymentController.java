package com.showspot.controller;

import com.showspot.entity.Booking;
import com.showspot.service.BookingService;
import com.showspot.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PaymentController {
    private final PaymentService paymentService;
    private final BookingService bookingService;

    @Autowired
    public PaymentController(PaymentService paymentService, BookingService bookingService) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
    }

    @GetMapping("/bookings/{id}/pay")
    public String showPaymentPage(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getAllBookings().stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("booking", booking);
        return "payment";
    }

    @PostMapping("/bookings/{id}/pay")
    public String processPayment(@PathVariable Long id, @RequestParam String method, @RequestParam double amount, @RequestParam String transactionId, Model model) {
        Booking booking = bookingService.getAllBookings().stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
        if (method.equals("CREDIT_CARD")) {
            paymentService.processCreditCardPayment(booking, amount, transactionId);
        } else {
            paymentService.processUPIPayment(booking, amount, transactionId);
        }
        model.addAttribute("paymentSuccess", true);
        return "payment-success";
    }
}
