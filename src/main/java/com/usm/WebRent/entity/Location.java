package com.usm.WebRent.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;
    private String city;
    private String phone;
    private String workingHouse;

    public Location(Long id, String address, String city, String phone, String workingHouse) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.workingHouse = workingHouse;
    }
}
