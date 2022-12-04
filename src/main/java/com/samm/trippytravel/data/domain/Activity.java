package com.samm.trippytravel.data.domain;

import lombok.Builder;
import lombok.Value;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Value
@Builder
@Document("activity")
public class Activity {
    @Id
    String _id;
    ObjectId trip_id;
    ObjectId day_id;
    boolean checked;
    String name;

    public Activity getActivity(Activity activity) {
        return Activity.builder()
                .trip_id(activity.getTrip_id())
                .day_id(activity.getDay_id())
                .checked(activity.isChecked())
                .name(activity.getName())
                .build();
    }
}
