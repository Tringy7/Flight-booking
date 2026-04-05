package com.flightbooking.flight_booking.service;

import com.flightbooking.flight_booking.config.Common;
import com.flightbooking.flight_booking.domain.User;
import com.flightbooking.flight_booking.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityExistsException(Common.USER_NOT_FOUND));
    }
    public User updateUser(User user) {
        return this.userRepository.save(user);
    }
}
