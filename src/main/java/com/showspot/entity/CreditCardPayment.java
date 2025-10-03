package com.showspot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@DiscriminatorValue("CREDIT_CARD")
public class CreditCardPayment implements Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private Status paymentStatus = Status.PENDING;

    private String transactionId;
    private LocalDateTime paymentTime = LocalDateTime.now();

    public enum Status {
        SUCCESS, FAILED, PENDING
    }
}
