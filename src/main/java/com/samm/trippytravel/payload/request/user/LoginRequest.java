package com.samm.trippytravel.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
public class LoginRequest {
    @NotBlank
    @JsonProperty("username")
    private final String username;

    @NotBlank
    @JsonProperty("password")
    private final String password;
}
