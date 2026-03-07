package com.usm.WebRent.service;

import com.usm.WebRent.entity.Car;

import java.util.List;

public interface CarService {
    Car save(Car car);
    List<Car> findAll();
    Car findById(Long id);
    Car update(Long id, Car car);
    void deleteById(Long id);
}
