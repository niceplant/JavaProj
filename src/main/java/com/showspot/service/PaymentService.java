package com.showspot.service;

import com.showspot.entity.*;
import com.showspot.repository.CreditCardPaymentRepository;
import com.showspot.repository.UPIPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PaymentService {
    private final CreditCardPaymentRepository creditCardPaymentRepository;
    private final UPIPaymentRepository upiPaymentRepository;

    @Autowired
    public PaymentService(CreditCardPaymentRepository creditCardPaymentRepository, UPIPaymentRepository upiPaymentRepository) {
        this.creditCardPaymentRepository = creditCardPaymentRepository;
        this.upiPaymentRepository = upiPaymentRepository;
    }

    public CreditCardPayment processCreditCardPayment(Booking booking, double amount, String transactionId) {
        CreditCardPayment payment = new CreditCardPayment();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setTransactionId(transactionId);
        payment.setPaymentStatus(CreditCardPayment.Status.SUCCESS);
        return creditCardPaymentRepository.save(payment);
    }

    public UPIPayment processUPIPayment(Booking booking, double amount, String transactionId) {
        UPIPayment payment = new UPIPayment();
        payment.setBooking(booking);
        payment.setAmount(amount);
        payment.setTransactionId(transactionId);
        payment.setPaymentStatus(UPIPayment.Status.SUCCESS);
        return upiPaymentRepository.save(payment);
    }

    public Optional<CreditCardPayment> getCreditCardPaymentByBookingId(Long bookingId) {
        return creditCardPaymentRepository.findAll().stream()
                .filter(p -> p.getBooking().getId().equals(bookingId))
                .findFirst();
    }

    public Optional<UPIPayment> getUPIPaymentByBookingId(Long bookingId) {
        return upiPaymentRepository.findAll().stream()
                .filter(p -> p.getBooking().getId().equals(bookingId))
                .findFirst();
    }
}
