package com.samm.trippytravel.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class MessageResponse {
    private final String message;
}
