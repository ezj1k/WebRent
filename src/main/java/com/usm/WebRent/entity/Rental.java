package com.usm.WebRent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;
    private Long carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalPrice;
    private RentalStatus status;
    private Long pickupLocationId;
    private LocalDateTime createdAt;

    public Rental(Long id, Long userId, Long carId, LocalDate startDate, LocalDate endDate, Double totalPrice, RentalStatus status, Long pickupLocationId, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.status = status;
        this.pickupLocationId = pickupLocationId;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }
}
