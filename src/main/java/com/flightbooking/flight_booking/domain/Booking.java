package com.flightbooking.flight_booking.domain;

import com.flightbooking.flight_booking.enumerate.status.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String bookingCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private BigDecimal totalPrice;
    private BigDecimal basePrice;
    private BigDecimal taxAmount;

    private Instant expiresAt;
    private Instant confirmedAt;

    private Instant createdAt;

    @OneToMany(mappedBy = "booking")
    private List<Passenger> passengers;

    @OneToMany(mappedBy = "booking")
    private List<Seat> seats;
}