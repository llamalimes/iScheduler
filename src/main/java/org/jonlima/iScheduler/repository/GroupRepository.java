package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
