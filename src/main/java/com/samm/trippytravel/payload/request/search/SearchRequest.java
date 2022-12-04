package com.samm.trippytravel.payload.request.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
public class SearchRequest {
    @NotBlank
    @JsonProperty("term")
    private final String term;

    @NotBlank
    @JsonProperty("location")
    private final String location;
}

