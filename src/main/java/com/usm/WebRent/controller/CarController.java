package com.usm.WebRent.controller;

import com.usm.WebRent.entity.Car;
import com.usm.WebRent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        List<Car> cars = carService.findAll();
        if (cars.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable Long id) {
        return ResponseEntity.ok(carService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Car car) {
        if (car.getBrand() == null || car.getLicensePlate() == null) {
            return ResponseEntity.badRequest().body("Brandul și Numărul auto sunt obligatorii!"); // 400
        }

        try {
            Car savedCar = carService.save(car);
            return ResponseEntity.status(201).body(savedCar); // 201
        } catch (Exception e) {
            return ResponseEntity.status(409).body("Conflict: Posibil număr de înmatriculare duplicat.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable Long id, @RequestBody Car car) {
        if (car.getId() != null && !car.getId().equals(id)) {
            return ResponseEntity.badRequest().build(); // 400
        }
        return ResponseEntity.ok(carService.update(id, car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}