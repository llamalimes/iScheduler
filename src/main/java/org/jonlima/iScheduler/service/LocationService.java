package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Location;
import org.jonlima.iScheduler.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    //CREATE
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }
    //READ
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }
    //UPDATE
    public Location updateLocation(Long id, Location location) {
        Optional<Location> existingLocationOptional = locationRepository.findById(id);
        if (existingLocationOptional.isPresent()) {
            Location existingLocation = existingLocationOptional.get();
            existingLocation.setName(location.getName());
            existingLocation.setAddress(location.getAddress());
            existingLocation.setLatitude(location.getLatitude());
            existingLocation.setLongitude(location.getLongitude());
            return locationRepository.save(existingLocation);
        } else {
            throw new IllegalArgumentException("Location not found with id: " + id);
        }
    }
    //DELETE
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
