package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
