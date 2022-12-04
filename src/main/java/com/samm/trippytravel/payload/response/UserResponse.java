package com.samm.trippytravel.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
public class UserResponse {
    @Id
    @NotBlank
    private final String _id;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
}
