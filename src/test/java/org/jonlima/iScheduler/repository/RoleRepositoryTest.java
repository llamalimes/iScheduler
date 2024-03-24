package org.jonlima.iScheduler.repository;

import jakarta.transaction.Transactional;
import org.jonlima.iScheduler.model.Role;
import org.jonlima.iScheduler.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {


    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void findByName() {

        Role foundRole = roleRepository.findByName("ROLE_ADMIN");

        assertEquals("ROLE_ADMIN", foundRole.getName());
    }
}
