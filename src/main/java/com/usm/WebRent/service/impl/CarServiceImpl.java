package com.usm.WebRent.service.impl;

import com.usm.WebRent.entity.Car;
import com.usm.WebRent.exception.CarNotFoundException;
import com.usm.WebRent.exception.CarSaveException;
import com.usm.WebRent.exception.EmptyCarListException;
import com.usm.WebRent.repository.CarRepository;
import com.usm.WebRent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Car save(Car car) {
        if (car.getBrand() == null || car.getLicensePlate() == null) {
            throw new CarSaveException("Brand-ul și numărul de înmatriculare sunt obligatorii!");
        }
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = carRepository.findAll();
        if (cars.isEmpty()) {
            throw new EmptyCarListException();
        }
        return cars;
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    @Override
    public Car update(Long id, Car carDetails) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));

        // Actualizăm câmpurile direct din obiectul primit
        car.setBrand(carDetails.getBrand());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setLicensePlate(carDetails.getLicensePlate());
        car.setCategory(carDetails.getCategory());
        car.setTransmission(carDetails.getTransmission());
        car.setEngineVolume(carDetails.getEngineVolume());
        car.setFuelType(carDetails.getFuelType());
        car.setPricePerDay(carDetails.getPricePerDay());
        car.setStatus(carDetails.getStatus());
        car.setFeatures(carDetails.getFeatures());

        return carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException(id);
        }
        carRepository.deleteById(id);
    }
}