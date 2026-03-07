package com.usm.WebRent.entity;

import com.usm.WebRent.entity.enums.CarCategory;
import com.usm.WebRent.entity.enums.CarFuelType;
import com.usm.WebRent.entity.enums.CarStatus;
import com.usm.WebRent.entity.enums.CarTransmision;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private Integer year;

    @Column(unique = true, nullable = false)
    private String licensePlate;
    @Enumerated(EnumType.STRING)
    private CarCategory category;

    @Enumerated(EnumType.STRING)
    private CarTransmision transmission;
    private Double engineVolume;

    @Enumerated(EnumType.STRING)
    private CarFuelType fuelType;
    private Double pricePerDay;

    @Enumerated(EnumType.STRING)
    private CarStatus status;

    @Column(name = "image_path")
    private String imageUrl;

//    @ElementCollection
//    @CollectionTable(name = "car_images", joinColumns = @JoinColumn(name = "car_id"))
//    @Column(name = "image_url")
//    private List<String> galleryImageUrls;

    @Column(length = 1000)
    private String features;


}
