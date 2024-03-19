package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.model.dto.AvailabilityForm;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface AvailabilityService {

    // Save or update an availability
    void saveAvailability(Availability availability);

    // Find availabilities by user
    List<Availability> findAvailabilitiesByUser(User user);

    // Find availabilities by user and day of the week
    List<Availability> findAvailabilitiesByUserAndDayOfWeek(User user, DayOfWeek dayOfWeek);

    // Initialize closed availability for a user
    void initializeClosedAvailability(User user);

    // Delete an availability
    void deleteAvailability(Availability availability);

    // Helper method to convert AvailabilityForm to Availability entity
    Availability convertToAvailability(AvailabilityForm availabilityForm, User user);

}
