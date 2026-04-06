package com.flightbooking.flight_booking.domain;

import com.flightbooking.flight_booking.enumerate.common.RoleName;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.lang.reflect.Type;
import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, length = 1000)
    private String password;
    private boolean status;
    private String phone;
    private Instant dob;
    private String idNumber;
    @Column(length = 1000)
    private String refreshToken;
    @Enumerated(EnumType.STRING)
    private RoleName role;
}
