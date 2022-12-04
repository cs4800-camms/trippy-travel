package com.samm.trippytravel.payload.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Builder
@Getter
@AllArgsConstructor
public class SignUpRequest {
    @NotBlank
    @JsonProperty("firstName")
    private final String firstName;

    @NotBlank
    @JsonProperty("lastName")
    private final String lastName;

    @NotBlank
    @Size(min = 3, max = 20)
    @JsonProperty("username")
    private final String username;

    @NotBlank
    @Size(max = 50)
    @JsonProperty("email")
    @Email
    private final String email;

    @NotBlank
    @JsonProperty("password")
    private final String password;

    private Set<String> roles;
}
