package com.usm.WebRent.service.impl;

import com.usm.WebRent.entity.Car;
import com.usm.WebRent.entity.Location;
import com.usm.WebRent.exception.EmptyListException;
import com.usm.WebRent.exception.LocationDeletionException;
import com.usm.WebRent.exception.LocationNotFoundException;
import com.usm.WebRent.exception.LocationSaveException;
import com.usm.WebRent.repository.LocationRepository;
import com.usm.WebRent.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    @Override
    public Location save(Location location) {
        if (location.getAddress() == null || location.getAddress().isEmpty()) {
            throw new LocationSaveException("Adresa este obligatorie!");
        }
        return locationRepository.save(location);
    }

    @Override
    public List<Location> findAll() {
        List<Location> locations = locationRepository.findAll();
        if (locations.isEmpty()) {
            throw new EmptyListException("Locații");
        }
        return locations;
    }

    @Override
    public Location findById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));
    }

    @Override
    public Location update(Long id, Location locationDetails) {
        // Folosim excepția de negăsit pentru a vedea dacă avem ce actualiza
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException(id));

        location.setAddress(locationDetails.getAddress());
        location.setCity(locationDetails.getCity());
        location.setPhone(locationDetails.getPhone());
        location.setWorkingHours(locationDetails.getWorkingHours());

        return locationRepository.save(location);
    }

    @Override
    public void deleteById(Long id) {
        if (!locationRepository.existsById(id)) {
            throw new LocationNotFoundException(id);
        }
        try {
            locationRepository.deleteById(id);
        } catch (Exception e) {
            throw new LocationDeletionException(id);
        }
    }
}
