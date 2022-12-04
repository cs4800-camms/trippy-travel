package com.samm.trippytravel.controllers;

import com.samm.trippytravel.data.domain.Trip;
import com.samm.trippytravel.payload.request.trip.CreateTripRequest;
import com.samm.trippytravel.payload.request.trip.UpdateTripRequest;
import com.samm.trippytravel.repository.TripRepository;
import com.samm.trippytravel.services.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/trips")
public class TripController {
    private final TripService tripService;
    private final TripRepository tripRepository;

    @GetMapping("/active")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Trip>> getTrips() {
        return new ResponseEntity<>(tripService.getTrips(), HttpStatus.OK);
    }

    @GetMapping("{userIdNumber}")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Trip>> getTripsForUser(
            @PathVariable("userIdNumber") String userIdNumber) {
        return new ResponseEntity<>(tripService.getTripsByUserId(userIdNumber), HttpStatus.OK);
    }

    @GetMapping("get-trip/{tripIdNumber}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Trip> getTripById(
            @PathVariable("tripIdNumber") String tripIdNumber) {
        return new ResponseEntity<>(tripService.getTripById(tripIdNumber), HttpStatus.OK);
    }

    @PostMapping("/create")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Trip> createTrip(
            @RequestBody CreateTripRequest createTripRequest) {
        return new ResponseEntity<>(tripService.addTrip(createTripRequest), HttpStatus.OK);
    }

    @PutMapping("{tripIdNumber}/update")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Trip> updateTrip(
            @PathVariable("tripIdNumber") String tripIdNumber,
            @RequestBody UpdateTripRequest updateTripRequest) {
        return new ResponseEntity<>(tripService.updateTrip(tripIdNumber, updateTripRequest), HttpStatus.OK);
    }

    @DeleteMapping("{tripIdNumber}/delete")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Trip> deleteTrip(@PathVariable("tripIdNumber") String tripIdNumber) {
        return new ResponseEntity<>(tripRepository.deleteById(tripIdNumber), HttpStatus.OK);
    }

    @DeleteMapping("/delete-all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete() {
        tripRepository.deleteAll();
    }
}