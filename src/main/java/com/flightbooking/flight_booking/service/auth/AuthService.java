package com.flightbooking.flight_booking.service.auth;

import com.flightbooking.flight_booking.config.Common;
import com.flightbooking.flight_booking.domain.User;
import com.flightbooking.flight_booking.dto.auth.LoginResponse;
import com.flightbooking.flight_booking.dto.auth.RegisterRequest;
import com.flightbooking.flight_booking.dto.auth.RegisterResponse;
import com.flightbooking.flight_booking.service.UserService;
import com.flightbooking.flight_booking.util.SecurityUtil;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final SecurityUtil securityUtil;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse handleAuthentication(Authentication authentication) {
        User user = this.userService.getUserByEmail(authentication.getName());

        String accessToken = this.securityUtil.createAccessToken(user);
        String refreshToken = this.securityUtil.createRefreshToken(user);

        User userRes = User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .dob(user.getDob())
                .build();

        user.setRefreshToken(refreshToken);
        this.userService.updateUser(user);

        return new LoginResponse(accessToken, refreshToken, userRes);
    }

    public ResponseCookie getCookie(String refreshToken) {
        return ResponseCookie.from("refresh-Token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(this.securityUtil.refreshTokenExpiration)
                .build();
    }

    public RegisterResponse handleRegister(RegisterRequest registerRequest) {
        if (this.userService.checkExistUser(registerRequest.getEmail())) {
            throw new EntityExistsException(Common.USER_EXISTS);
        }

        User user = User.builder()
                .email(registerRequest.getEmail())
                .fullName(registerRequest.getFirstName() + " " + registerRequest.getLastName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phone(registerRequest.getPhoneNumber())
                .build();
        user = this.userService.saveUser(user);

        return RegisterResponse.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhone())
                .build();
    }
}
