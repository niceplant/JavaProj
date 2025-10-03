package com.showspot.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MOVIE")
public class MovieTicket extends Ticket {
    // Add movie-specific fields if needed
}
