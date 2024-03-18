package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.User;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public interface AvailabilityService {
    void saveAvailability(Availability availability);

    void saveAvailabilityBlocks(Availability availability);
    List<Availability> findAvailabilitiesByUser(User user);

    List<Availability> findAvailabilitiesByUserAndDayOfWeek(User user, DayOfWeek dayOfWeek);

    void initializeClosedAvailability(User user);

}
