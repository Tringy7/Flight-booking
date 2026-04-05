package com.flightbooking.flight_booking.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(name = "created_at",nullable = true)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = true)
    private Instant updatedAt;

    @CreatedBy
    @Column(name = "created_by",nullable = true)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by",nullable = true)
    private String updatedBy;

    @Column(name = "is_deleted",nullable = true)
    private Boolean isDeleted;
}
