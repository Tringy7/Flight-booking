package com.flightbooking.flight_booking.domain;

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
    @NotBlank
    @Column(nullable = false)
    private String fullName;
    @Email()
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private boolean status;
    private String phone;
    private Instant dob;
    // CCCD
    private String idNumber;
    @Column(length = 1000)
    private String refreshToken;
    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
