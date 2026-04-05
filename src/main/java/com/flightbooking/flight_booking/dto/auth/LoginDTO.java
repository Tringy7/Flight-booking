package com.flightbooking.flight_booking.dto.auth;

import com.flightbooking.flight_booking.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class LoginDTO {
    @NotBlank(message = "Email is required")
    @Size(min = 3, max = 50, message = "Email must be between 3 and 50 characters")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private User user;
}
