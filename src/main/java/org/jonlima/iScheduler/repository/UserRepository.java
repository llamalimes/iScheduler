package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);
    Users findUserById(long id);
}
