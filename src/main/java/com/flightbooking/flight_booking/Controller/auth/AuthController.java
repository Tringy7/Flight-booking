package com.flightbooking.flight_booking.Controller.auth;

import com.flightbooking.flight_booking.domain.User;
import com.flightbooking.flight_booking.dto.auth.LoginDTO;
import com.flightbooking.flight_booking.dto.auth.LoginRes;
import com.flightbooking.flight_booking.service.UserService;
import com.flightbooking.flight_booking.service.auth.AuthService;
import com.flightbooking.flight_booking.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@Valid @RequestBody LoginDTO loginDTO) throws Exception{
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail(), loginDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        LoginRes loginRes = this.authService.handleAuthentication(authentication);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, this.authService.getCookie(loginRes.getRefreshToken()).toString())
                .body(loginRes);
    }
}
