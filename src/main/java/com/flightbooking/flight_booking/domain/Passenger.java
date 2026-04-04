package com.flightbooking.flight_booking.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "passengers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private String fullName;
    private Instant dob;
    private String idNumber;
}
