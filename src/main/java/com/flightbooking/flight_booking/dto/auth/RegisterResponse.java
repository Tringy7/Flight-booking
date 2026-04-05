package com.flightbooking.flight_booking.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterResponse {
    private Long userId;
    private String fullName;
    private String email;
    private String phoneNumber;
}
