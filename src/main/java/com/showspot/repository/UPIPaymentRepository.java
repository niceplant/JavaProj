package com.showspot.repository;

import com.showspot.entity.UPIPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UPIPaymentRepository extends JpaRepository<UPIPayment, Long> {
}
