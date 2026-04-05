package com.flightbooking.flight_booking.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRes {
    private String accessToken;
    @JsonIgnore
    private String refreshToken;
}
