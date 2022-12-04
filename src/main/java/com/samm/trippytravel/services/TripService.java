package com.samm.trippytravel.services;

import com.samm.trippytravel.data.domain.Trip;
import com.samm.trippytravel.payload.request.trip.CreateTripRequest;
import com.samm.trippytravel.payload.request.trip.UpdateTripRequest;
import com.samm.trippytravel.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TripService {
    private final TripRepository tripRepository;

    public Trip addTrip(CreateTripRequest createTripRequest) {
        return tripRepository.insert(Trip.builder()
                .user_id(createTripRequest.getUser_id())
                .name(createTripRequest.getName())
                .destination(createTripRequest.getDestination())
                .startDate(createTripRequest.getStartDate())
                .endDate(createTripRequest.getEndDate())
                .build());
    }

    public Trip updateTrip(String idNumber, UpdateTripRequest updateTripRequest) {
        return tripRepository.save(Trip.builder()
                ._id(idNumber)
                .user_id(updateTripRequest.getUser_id())
                .name(updateTripRequest.getName())
                .destination(updateTripRequest.getDestination())
                .startDate(updateTripRequest.getStartDate())
                .endDate(updateTripRequest.getEndDate())
                .build());
    }

    public List<Trip> getTrips() {
        return new ArrayList<>(tripRepository.findAll());
    }

    public List<Trip> getTripsByUserId(String userIdNumber) {
        return new ArrayList<>(tripRepository.getTripsByUserId(userIdNumber));
    }

    public Trip getTripById(String tripIdNumber) {
        return tripRepository.getTripById(tripIdNumber);
    }
}
