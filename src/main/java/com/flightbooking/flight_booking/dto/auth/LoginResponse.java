package com.flightbooking.flight_booking.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.flightbooking.flight_booking.domain.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;
    @JsonIgnore
    private String refreshToken;
    private User user;
}
