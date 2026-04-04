package com.usm.WebRent.service.impl;

import com.usm.WebRent.entity.Car;
import com.usm.WebRent.repository.CarRepository;
import com.usm.WebRent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car save(Car car, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadDir = "src/main/resources/static/media/cars/";
            Path dirPath = Paths.get(uploadDir);
            Files.createDirectories(dirPath);

            String originalFilename = imageFile.getOriginalFilename();
            String extension = (originalFilename != null && originalFilename.contains("."))
                    ? originalFilename.substring(originalFilename.lastIndexOf("."))
                    : ".jpg";

            String fileName = UUID.randomUUID() + extension;
            Files.write(dirPath.resolve(fileName), imageFile.getBytes());

            car.setImageUrl("/media/cars/" + fileName);
        } else {
            // Dacă nu s-a încărcat o imagine nouă, dar mașina există deja (Update)
            if (car.getId() != null) {
                Car existing = carRepository.findById(car.getId()).orElse(null);
                if (existing != null) {
                    car.setImageUrl(existing.getImageUrl());
                }
            }
        }

        return carRepository.save(car);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(()-> new RuntimeException("Car with id:" + id + "doesn't exist"));
    }

    @Override
    public Car update(Long id, Car carDetails) {
        Car car = findById(id);

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
        car.setImageUrl(carDetails.getImageUrl());
        car.setFeatures(carDetails.getFeatures());

        return carRepository.save(car);
    }

    @Override
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }
}
