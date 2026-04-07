package com.usm.WebRent.controller;

import com.usm.WebRent.entity.Rental;
import com.usm.WebRent.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @GetMapping
    public ResponseEntity<List<Rental>> getAll() {
        return ResponseEntity.ok(rentalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Rental> create(@RequestBody Rental rental) {
        return ResponseEntity.status(201).body(rentalService.save(rental));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rental> update(@PathVariable Long id, @RequestBody Rental rental) {
        return ResponseEntity.ok(rentalService.update(id, rental));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rentalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}