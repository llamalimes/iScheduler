package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.repository.AvailabilityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.DayOfWeek;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AvailabilityRepositoryTest {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Test
    public void findByUser() {
        User user = new User();
        user.setId(1L);
        // userRepository.save(user); // Assuming you have a UserRepository

        Availability availability = new Availability();
        availability.setUser(user);
        availability.setDayOfWeek(DayOfWeek.MONDAY);
        availabilityRepository.save(availability);

        List<Availability> foundAvailabilities = availabilityRepository.findByUser(user);

        assertEquals(1, foundAvailabilities.size());
        assertEquals(DayOfWeek.MONDAY, foundAvailabilities.get(0).getDayOfWeek());
    }

    @Test
    public void findByUserAndDayOfWeek() {
        User user = new User();
        user.setId(2L);
        // userRepository.save(user); // Assuming you have a UserRepository

        Availability availability = new Availability();
        availability.setUser(user);
        availability.setDayOfWeek(DayOfWeek.TUESDAY);
        availabilityRepository.save(availability);

        List<Availability> foundAvailabilities = availabilityRepository.findByUserAndDayOfWeek(user, DayOfWeek.TUESDAY);

        assertEquals(1, foundAvailabilities.size());
        assertEquals(DayOfWeek.TUESDAY, foundAvailabilities.get(0).getDayOfWeek());
    }
}
