package com.showspot.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {
    // Add customer-specific fields if needed
}
