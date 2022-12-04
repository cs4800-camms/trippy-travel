package com.samm.trippytravel.data.domain;

import lombok.Builder;
import lombok.Value;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Value
@Builder
@Document("day")
public class Day {
    @Id
    String _id;
    ObjectId trip_id;
    Date date;

    public Day getDay(Day day) {
        return Day.builder()
                .trip_id(day.getTrip_id())
                .date(day.getDate())
                .build();
    }
}
