package com.samm.trippytravel.payload.request.activity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
public class CreateActivityRequest {
    @NotBlank
    @JsonProperty("trip_id")
    private final ObjectId trip_id;

    @NotBlank
    @JsonProperty("day_id")
    private final ObjectId day_id;

    @NotNull
    @JsonProperty("checked")
    private final boolean checked;

    @NotBlank
    @JsonProperty("name")
    private final String name;
}
