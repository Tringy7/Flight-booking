package com.flightbooking.flight_booking.domain;

import com.flightbooking.flight_booking.enumerate.common.SeatClass;
import com.flightbooking.flight_booking.enumerate.status.SeatStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatClass seatClass;

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    private Instant lockedUntil;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}