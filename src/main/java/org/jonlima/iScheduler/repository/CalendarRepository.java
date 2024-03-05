package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
}
