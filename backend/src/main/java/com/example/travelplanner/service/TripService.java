package com.example.travelplanner.service;

import com.example.travelplanner.entity.Trip;
import com.example.travelplanner.exception.ResourceNotFoundException;
import com.example.travelplanner.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<Trip> getTripsByUserId(Long userId) {
        return tripRepository.findByUserId(userId);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found with id " + id));
    }

    public Trip updateTrip(Long id, Trip tripDetails) {
        Trip trip = getTripById(id);
        trip.setTitle(tripDetails.getTitle());
        trip.setDescription(tripDetails.getDescription());
        trip.setStartDate(tripDetails.getStartDate());
        trip.setEndDate(tripDetails.getEndDate());
        return tripRepository.save(trip);
    }

    public void deleteTrip(Long id) {
        // Check if the trip exists before deleting
        if (!tripRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trip not found with id " + id);
        }
        tripRepository.deleteById(id);
    }
}