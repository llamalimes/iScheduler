package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.TimeBlock;
import org.jonlima.iScheduler.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AvailabilityRepositoryTest {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private UserRepository userRepository;

    private Users testUser;

    @BeforeEach
    void setUp() {
        // Clean up
        userRepository.deleteAll();
        availabilityRepository.deleteAll();

        // Setup user
        testUser = new Users();
        testUser.setName("John Doe");
        testUser.setEmail("johndoe@example.com");
        testUser.setPassword("test123");
        userRepository.save(testUser);

        // Setup Availability
        Availability availability1 = new Availability();
        availability1.setUsers(testUser);
        availability1.setDayOfWeek(DayOfWeek.MONDAY);
        availability1.setTimeBlocks(Arrays.asList(new TimeBlock(LocalTime.of(9, 0), LocalTime.of(17, 0))));
        availabilityRepository.save(availability1);

        Availability availability2 = new Availability();
        availability2.setUsers(testUser);
        availability2.setDayOfWeek(DayOfWeek.FRIDAY);
        availability2.setTimeBlocks(Arrays.asList(new TimeBlock(LocalTime.of(10, 0), LocalTime.of(15, 0))));
        availabilityRepository.save(availability2);
    }

    @Test
    void testFindByUsers() {
        var availabilities = availabilityRepository.findByUsers(testUser);
        assertThat(availabilities).hasSize(2);
        assertThat(availabilities.getFirst().getDayOfWeek()).isEqualTo(DayOfWeek.MONDAY);
    }

    @Test
    void testFindByUsersAndDayOfWeek() {
        var availabilities = availabilityRepository.findByUsersAndDayOfWeek(testUser, DayOfWeek.MONDAY);
        assertThat(availabilities).hasSize(1);
        assertThat(availabilities.getFirst().getTimeBlocks()).hasSize(1);
        assertThat(availabilities.getFirst().getTimeBlocks().getFirst().getStartTime()).isEqualTo(LocalTime.of(9, 0));
    }
}
