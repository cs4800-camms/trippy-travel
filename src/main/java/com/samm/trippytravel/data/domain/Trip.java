package com.samm.trippytravel.data.domain;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Value
@Builder
@Document("trip")
public class Trip {
    @Id
    String _id;
    long userId;
    String name;
    String destination;
    Date startDate;
    Date endDate;

    public static Trip getTrips(Trip trip) {
        return Trip.builder()
                .userId(trip.getUserId())
                .name(trip.getName())
                .destination(trip.getDestination())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .build();
    }
}
