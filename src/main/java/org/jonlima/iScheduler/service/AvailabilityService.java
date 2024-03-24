package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.TimeBlock;
import org.jonlima.iScheduler.model.Users;
import org.jonlima.iScheduler.model.dto.AvailabilityForm;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

public interface AvailabilityService {

    // Save or update an availability
    void saveAvailability(Availability availability);

    // Find availabilities by users
    List<Availability> findAvailabilitiesByUser(Users users);

    // Find availabilities by users and day of the week
    List<Availability> findAvailabilitiesByUserAndDayOfWeek(Users users, DayOfWeek dayOfWeek);

    Map<DayOfWeek, List<Availability>> findAvailabilitiesGroupedByDay(Users users);

    // Initialize closed availability for a users
    void initializeClosedAvailability(Users users);

    // Delete an availability
    void deleteAvailability(Availability availability);

    // Helper method to convert AvailabilityForm to Availability entity
    Availability convertToAvailability(AvailabilityForm availabilityForm, Users users);

    TimeBlock findCommonAvailability(Users users, Users friend);
}
