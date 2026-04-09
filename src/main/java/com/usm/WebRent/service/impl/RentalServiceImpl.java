package com.usm.WebRent.service.impl;

import com.usm.WebRent.entity.Car;
import com.usm.WebRent.entity.Location;
import com.usm.WebRent.entity.Rental;
import com.usm.WebRent.entity.Users;
import com.usm.WebRent.entity.enums.RentalStatus;
import com.usm.WebRent.exception.*;
import com.usm.WebRent.repository.RentalRepository;
import com.usm.WebRent.service.CarService;
import com.usm.WebRent.service.RentalService;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final CarService carService;


    @Override
    public Rental save(Rental rental) {
        if (rental.getId() == null) {
            rental.setCreatedAt(java.time.LocalDateTime.now());
        }

        if (rental.getCar() == null) throw new CarNotFoundException(null);
        if (rental.getUser() == null) throw new UserNotFoundException(null);

        if (rental.getCar() != null && rental.getCar().getId() != null) {
            Car fullCar = carService.findById(rental.getCar().getId());
            rental.setCar(fullCar);

            if (rental.getStartDate() != null && rental.getEndDate() != null) {
                if (rental.getEndDate().isBefore(rental.getStartDate())) {
                    throw new InvalidRentalDatesException("Data de returnare nu poate fi înainte de data ridicării!");
                }

                long days = java.time.temporal.ChronoUnit.DAYS.between(
                        rental.getStartDate(),
                        rental.getEndDate()
                );

                if (days <= 0) days = 1;

                double calculatedPrice = days * fullCar.getPricePerDay();
                rental.setTotalPrice(calculatedPrice);
            }
        }

        if (rental.getEndDate().isBefore(rental.getStartDate())) {
            throw new InvalidRentalDatesException("Perioadă invalidă!");
        }

        return rentalRepository.save(rental);
    }

    @Override
    public List<Rental> findAll() {
        List<Rental> rentals = rentalRepository.findAll();
        if (rentals.isEmpty()) {
            throw new EmptyListException("Rentals");
        }
        return rentals;
    }

    @Override
    public Rental findById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException(id));
    }

    @Override
    public Rental update(Long id, Rental rentalDetails) {
        Rental rental = findById(id);

        rental.setCar(rentalDetails.getCar());
        rental.setStatus(rentalDetails.getStatus());
        rental.setCreatedAt(rentalDetails.getCreatedAt());
        rental.setUser(rentalDetails.getUser());
        rental.setEndDate(rentalDetails.getEndDate());
        rental.setPickupLocation(rentalDetails.getPickupLocation());
        rental.setStartDate(rentalDetails.getStartDate());
        rental.setTotalPrice(rentalDetails.getTotalPrice());

        return rentalRepository.save(rental);
    }

    @Override
    public void deleteById(Long id) {
        Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new RentalNotFoundException(id));

        if ("COMPLETED".equals(rental.getStatus().toString())) {
            throw new RentalActionException("Nu poți șterge o rezervare care a fost deja finalizată!");
        }
        rentalRepository.deleteById(id);
    }
}
