package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
