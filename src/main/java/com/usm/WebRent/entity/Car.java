package com.usm.WebRent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String brand;
    private String model;
    private Integer year;

    @Column(unique = true, nullable = false)
    private String licensePlate;

    private String category;
    private String transmission;
    private Double engineVolume;
    private String fuelType;
    private Double pricePerDay;
    @Enumerated(EnumType.STRING)
    private CarStatus status;
    private String imageUrl;

//    @ElementCollection
//    @CollectionTable(name = "car_images", joinColumns = @JoinColumn(name = "car_id"))
//    @Column(name = "image_url")
//    private List<String> galleryImageUrls;

    @Column(length = 1000)
    private String features;

    public Car(String brand, String model, Integer year, String licensePlate,
               String category, String transmission, Double engineVolume,
               String fuelType, Double pricePerDay, CarStatus status,
               String imageUrl,  String features) {

        this.brand = brand;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.category = category;
        this.transmission = transmission;
        this.engineVolume = engineVolume;
        this.fuelType = fuelType;
        this.pricePerDay = pricePerDay;
        this.status = status;
        this.imageUrl = imageUrl;
//        this.galleryImageUrls = galleryImageUrls;
        this.features = features;
    }
}
