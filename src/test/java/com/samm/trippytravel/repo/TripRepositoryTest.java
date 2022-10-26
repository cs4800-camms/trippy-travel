package com.samm.trippytravel.repo;

import com.samm.trippytravel.data.domain.Trip;
import com.samm.trippytravel.repository.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
public class TripRepositoryTest {
    @Autowired
    @Mock
    private TripRepository tripRepository;
    private Trip addedTrip;
    private Trip trip;

    private final String ID ="abcdefg";
    private final long USER_ID = 1;
    private final String NAME = "Family Trip";
    private final String DESTINATION = "Tokyo, Japan";
    private final Date START = new Date(2022, Calendar.NOVEMBER, 21);
    private final Date END = new Date(2022, Calendar.NOVEMBER, 25);

    @BeforeEach
    void setUp() {
        this.trip = Trip.builder()
                ._id(ID)
                .userId(USER_ID)
                .name(NAME)
                .destination(DESTINATION)
                .startDate(START)
                .endDate(END)
                .build();

        when(tripRepository.insert(trip)).thenReturn(Trip.builder()
                ._id(ID)
                .userId(USER_ID)
                .name(NAME)
                .destination(DESTINATION)
                .startDate(START)
                .endDate(END)
                .build());
        this.addedTrip = this.tripRepository.insert(trip);
    }

    @Test
    void isTripAdded() {
        log.info("addedTrip: " + addedTrip.toString());
        assertThat(this.trip).isEqualTo(this.addedTrip);
    }

    @Test
    void isTripDeleted() {
        when(this.tripRepository.deleteById(ID)).thenReturn(Trip.builder()
                ._id(ID)
                .userId(USER_ID)
                .name(NAME)
                .destination(DESTINATION)
                .startDate(START)
                .endDate(END)
                .build());
        Trip deletedTrip = this.tripRepository.deleteById(ID);

        log.info("deletedTrip: " + deletedTrip.toString());
        assertThat(this.addedTrip).isEqualTo(deletedTrip);
    }
}
