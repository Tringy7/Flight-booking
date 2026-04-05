package com.flightbooking.flight_booking.service.auth;

import com.flightbooking.flight_booking.domain.User;
import com.flightbooking.flight_booking.dto.auth.LoginRes;
import com.flightbooking.flight_booking.service.UserService;
import com.flightbooking.flight_booking.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserService userService;
    private final SecurityUtil securityUtil;

    public LoginRes handleAuthentication(Authentication authentication) {
        User user = this.userService.getUserByEmail(authentication.getName());

        String accessToken = this.securityUtil.createAccessToken(user);
        String refreshToken = this.securityUtil.createRefreshToken(user);

        user.setRefreshToken(refreshToken);
        this.userService.updateUser(user);

        return new LoginRes(accessToken, refreshToken);
    }

    public ResponseCookie getCookie(String refreshToken) {
        return ResponseCookie.from("refresh-Token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(this.securityUtil.refreshTokenExpiration)
                .build();
    }
}
