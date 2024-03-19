package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.TimeBlock;
import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.model.dto.AvailabilityForm;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface AvailabilityService {

    // Save or update an availability
    void saveAvailability(Availability availability);

    // Find availabilities by user
    List<Availability> findAvailabilitiesByUser(User user);

    Availability findByUserId(long userId);

    // Find availabilities by user and day of the week
    List<Availability> findAvailabilitiesByUserAndDayOfWeek(User user, DayOfWeek dayOfWeek);

    Map<DayOfWeek, List<Availability>> findAvailabilitiesGroupedByDay(User user);

    // Initialize closed availability for a user
    void initializeClosedAvailability(User user);

    // Delete an availability
    void deleteAvailability(Availability availability);

    // Helper method to convert AvailabilityForm to Availability entity
    Availability convertToAvailability(AvailabilityForm availabilityForm, User user);

    public List<TimeBlock> findOverlappingTimeBlocks(List<Availability> userAvailabilities, List<Availability> friendAvailabilities, int meetingDuration);

    TimeBlock findEarliestTimeBlock(List<TimeBlock> timeBlocks);
}
