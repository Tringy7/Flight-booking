package com.flightbooking.flight_booking.domain;

import com.flightbooking.flight_booking.enumerate.status.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "flights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String flightNumber;

    private String airline;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    private Instant departureTime;
    private Instant arrivalTime;

    private BigDecimal economyPrice;
    private BigDecimal businessPrice;
    private BigDecimal firstPrice;

    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;
}