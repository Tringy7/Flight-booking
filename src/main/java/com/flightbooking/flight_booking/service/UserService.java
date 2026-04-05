package com.flightbooking.flight_booking.service;

import com.flightbooking.flight_booking.config.Common;
import com.flightbooking.flight_booking.domain.User;
import com.flightbooking.flight_booking.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean checkExistUser(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(Common.USER_NOT_FOUND));
    }
    public User updateUser(User user) {
        return userRepository.save(user);
    }
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
