package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.repository.AvailabilityRepository;
import org.jonlima.iScheduler.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

    private AvailabilityRepository availabilityRepository;

    @Autowired
    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
    }

    @Override
    public void saveAvailability(Availability availability) {
        availabilityRepository.save(availability);
    }

    @Override
    public void saveAvailabilityBlocks(Availability availability) {
        availabilityRepository.save(availability);
    }
    @Override
    public List<Availability> findAvailabilitiesByUser(User user) {
        return availabilityRepository.findByUser(user);
    }

    @Override
    public List<Availability> findAvailabilitiesByUserAndDayOfWeek(User user, DayOfWeek dayOfWeek) {
        return availabilityRepository.findByUserAndDayOfWeek(user, dayOfWeek);
    }

    @Override
    public void initializeClosedAvailability(User user) {
        Availability availability = new Availability();
        availability.setUser(user);
        availabilityRepository.save(availability);
    }


}