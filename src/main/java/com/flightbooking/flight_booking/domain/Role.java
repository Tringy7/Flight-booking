package com.flightbooking.flight_booking.domain;

import com.flightbooking.flight_booking.enumerate.common.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private RoleName name;

    private String description;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
