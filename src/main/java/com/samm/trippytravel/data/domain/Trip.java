package com.samm.trippytravel.data.domain;

import lombok.Builder;
import lombok.Value;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Value
@Builder
@Document("trip")
public class Trip {
    @Id
    String _id;
    ObjectId user_id;
    String name;
    String destination;
    Date startDate;
    Date endDate;

    public static Trip getTrips(Trip trip) {
        return Trip.builder()
                .user_id(trip.getUser_id())
                .name(trip.getName())
                .destination(trip.getDestination())
                .startDate(trip.getStartDate())
                .endDate(trip.getEndDate())
                .build();
    }
}
