package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.TimeBlock;
import org.jonlima.iScheduler.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;


class AvailabilityServiceImplTest {

    private AvailabilityServiceImpl availabilityService;

    private Users user1, user2;

    @BeforeEach
    void setUp() {
        // Initialize the service
        availabilityService = new AvailabilityServiceImpl(null); // Assuming no autowiring is needed for this test

        // Set up test users and their availabilities
        user1 = createUserWithAvailabilities(DayOfWeek.MONDAY, new TimeBlock(LocalTime.of(9, 0), LocalTime.of(12, 0)));
        user2 = createUserWithAvailabilities(DayOfWeek.MONDAY, new TimeBlock(LocalTime.of(11, 0), LocalTime.of(14, 0)));
    }

    @Test
    void testFindCommonAvailability() {
        TimeBlock commonAvailability = availabilityService.findCommonAvailability(user1, user2);
        assertEquals(LocalTime.of(11, 0), commonAvailability.getStartTime());
        assertEquals(LocalTime.of(12, 0), commonAvailability.getEndTime());
    }
    private Users createUserWithAvailabilities(DayOfWeek dayOfWeek, TimeBlock... timeBlocks) {
        Users user = new Users();
        Availability availability = new Availability();
        availability.setDayOfWeek(dayOfWeek);
        availability.setTimeBlocks(new ArrayList<>(Arrays.asList(timeBlocks)));

        user.setAvailabilities(Arrays.asList(availability));
        return user;
    }
}
