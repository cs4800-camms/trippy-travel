package com.samm.trippytravel.data.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
public class UpdateTripRequest {
    @NotBlank
    @JsonProperty("userId")
    private final long userId;

    @NotBlank
    @JsonProperty("name")
    private final String name;

    @NotBlank
    @JsonProperty("destination")
    private final String destination;

    @NotBlank
    @JsonProperty("startDate")
    private final Date startDate;

    @NotBlank
    @JsonProperty("endDate")
    private final Date endDate;
}
