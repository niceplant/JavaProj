package com.moviebooking.repository;

import com.moviebooking.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    List<Screen> findByTheaterIdAndActiveTrue(Long theaterId);
    List<Screen> findByActiveTrue();
}

// -------------------------------------------------------

// SeatRepository.java
package com.moviebooking.repository;

import com.moviebooking.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByScreenIdAndActiveTrue(Long screenId);
    List<Seat> findByScreenIdAndRowNameAndActiveTrue(Long screenId, String rowName);
}

// -------------------------------------------------------

// BookedSeatRepository.java
package com.moviebooking.repository;

import com.moviebooking.entity.BookedSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookedSeatRepository extends JpaRepository<BookedSeat, Long> {
    List<BookedSeat> findByShowTimeIdAndStatus(Long showTimeId, String status);
    List<BookedSeat> findByBookingId(Long bookingId);
    
    @Query("SELECT bs.seat.id FROM BookedSeat bs WHERE bs.showTime.id = ?1 AND bs.status = 'BOOKED'")
    List<Long> findBookedSeatIdsByShowTimeId(Long showTimeId);
}
