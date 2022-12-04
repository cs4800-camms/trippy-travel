package com.samm.trippytravel.payload.request.day;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
public class UpdateDayRequest {
    @NotBlank
    @JsonProperty("trip_id")
    private final ObjectId trip_id;

    @NotBlank
    @JsonProperty("name")
    private final String name;

    @NotBlank
    @JsonProperty("date")
    private final Date date;
}
