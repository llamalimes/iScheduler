package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {

    List<Availability> findByUsers(Users users);

    List<Availability> findByUsersAndDayOfWeek(Users users, DayOfWeek dayOfWeek);

    //Availability save(Availability availability);

    //void delete (Availability availability);
}