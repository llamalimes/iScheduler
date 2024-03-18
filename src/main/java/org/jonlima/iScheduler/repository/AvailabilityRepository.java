package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findByUser(User user);

    List<Availability> findByUserAndDayOfWeek(User user, DayOfWeek dayOfWeek);
}