package com.samm.trippytravel.service;

import com.samm.trippytravel.data.domain.Trip;
import com.samm.trippytravel.data.requests.CreateTripRequest;
import com.samm.trippytravel.data.requests.UpdateTripRequest;
import com.samm.trippytravel.repository.TripRepository;
import com.samm.trippytravel.services.TripService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class TripServiceTest {
    @Mock
    private TripRepository tripRepository;
    @Mock
    private TripService tripService;
    private CreateTripRequest createTripRequest;
    private Trip trip;
    private UpdateTripRequest updateTripRequest;

    private final String ID = "abcdefg";
    private final Date START = new Date(2022, Calendar.NOVEMBER, 21);
    private final Date END = new Date(2022, Calendar.NOVEMBER, 25);

    @BeforeEach
    void setUp() {
        this.tripService = new TripService(this.tripRepository);
        this.createTripRequest = CreateTripRequest.builder()
                .userId(3)
                .name("Mary")
                .destination("Rome")
                .startDate(START)
                .endDate(END)
                .build();
        this.trip = Trip.builder()
                ._id(ID)
                .userId(4)
                .name("Mel")
                .destination("Japan")
                .startDate(START)
                .endDate(END)
                .build();
        this.updateTripRequest = UpdateTripRequest.builder()
                .userId(4)
                .name("Mel T.")
                .destination("Taipei")
                .startDate(START)
                .endDate(END)
                .build();
    }

    @Test
    void getTrips() {
        when(tripService.getTrips()).thenReturn(List.of(trip));
        List<Trip> listOfTrips = tripService.getTrips();

        verify(tripRepository).findAll();
        assertThat(listOfTrips.get(0)).isEqualTo(trip);
    }

    @Test
    void addTrip() {
        when(tripService.addTrip(createTripRequest))
                .thenReturn(Trip.builder()
                        .userId(3)
                        .name("Mary")
                        .destination("Rome")
                        .startDate(START)
                        .endDate(END)
                        .build());
        Trip addedTrip = tripService.addTrip(createTripRequest);

        log.info("addedTrip: " + addedTrip.toString());
        verify(tripRepository).insert(addedTrip);
        assertThat(createTripRequest.getUserId()).isEqualTo(addedTrip.getUserId());
        assertThat(createTripRequest.getDestination()).isEqualTo(addedTrip.getDestination());
        assertThat(createTripRequest.getName()).isEqualTo(addedTrip.getName());
        assertThat(createTripRequest.getStartDate()).isEqualTo(addedTrip.getStartDate());
        assertThat(createTripRequest.getEndDate()).isEqualTo(addedTrip.getEndDate());
    }

    @Test
    void updateTrip() {
        when(tripService.updateTrip(trip.get_id(), updateTripRequest))
                .thenReturn(Trip.builder()
                        ._id(ID)
                        .userId(4)
                        .name("Mel T.")
                        .destination("Taipei")
                        .startDate(START)
                        .endDate(END)
                        .build());
        Trip updatedTrip = tripService.updateTrip(trip.get_id(), updateTripRequest);

        log.info("updatedTrip: " + updatedTrip.toString());
        verify(tripRepository).save(updatedTrip);
        assertThat(updateTripRequest.getDestination()).isEqualTo(updatedTrip.getDestination());
        assertThat(updateTripRequest.getName()).isEqualTo(updatedTrip.getName());
    }
}
