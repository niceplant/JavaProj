package com.showspot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    private LocalDateTime bookingTime = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Status status = Status.CONFIRMED;

    public enum Status {
        CONFIRMED, CANCELLED
    }
}
