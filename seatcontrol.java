package com.moviebooking.controller;

import com.moviebooking.entity.Seat;
import com.moviebooking.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "*")
public class SeatController {
    
    @Autowired
    private SeatService seatService;
    
    @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<Seat>> getSeatsByScreen(@PathVariable Long screenId) {
        return ResponseEntity.ok(seatService.getSeatsByScreen(screenId));
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<Seat>> getAvailableSeats(
            @RequestParam Long screenId,
            @RequestParam Long showTimeId) {
        return ResponseEntity.ok(seatService.getAvailableSeats(screenId, showTimeId));
    }
    
    @PostMapping("/generate")
    public ResponseEntity<String> generateSeats(
            @RequestParam Long screenId,
            @RequestParam int rows,
            @RequestParam int seatsPerRow) {
        seatService.generateSeatsForScreen(screenId, rows, seatsPerRow);
        return ResponseEntity.ok("Seats generated successfully");
    }
}
