package com.example.travelplanner.controller;

import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.exception.ResourceNotFoundException;
import com.example.travelplanner.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PostMapping
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        return ResponseEntity.ok(tripService.createTrip(trip));
    }

    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }
    // Endpoint to get a trip by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
        Trip trip = tripService.getTripById(id);
        return ResponseEntity.ok(trip);
    }

    // Endpoint to get trips by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Trip>> getTripsByUserId(@PathVariable Long userId) {
        List<Trip> trips = tripService.getTripsByUserId(userId);
        if (trips.isEmpty()) {
            throw new ResourceNotFoundException("No trips found for user with id " + userId);
        }
        return ResponseEntity.ok(trips);
    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Trip> getTripById(@PathVariable Long id) {
//        return ResponseEntity.ok(tripService.getTripById(id));
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Trip> updateTrip(@PathVariable Long id, @RequestBody Trip tripDetails) {
        return ResponseEntity.ok(tripService.updateTrip(id, tripDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }
}
