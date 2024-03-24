package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    private Role adminRole;
    private Role userRole;

    @BeforeEach
    void setUp() {
        roleRepository.deleteAll();

        adminRole = new Role();
        adminRole.setName("ADMIN");
        roleRepository.save(adminRole);

        userRole = new Role();
        userRole.setName("USER");
        roleRepository.save(userRole);
    }

    @Test
    void testFindByName() {
        Role foundRole = roleRepository.findByName("ADMIN");
        assertThat(foundRole).isNotNull();
        assertThat(foundRole.getName()).isEqualTo("ADMIN");
    }

    @Test
    void testFindByName_NotFound() {
        Role notFoundRole = roleRepository.findByName("NON_EXISTENT_ROLE");
        assertThat(notFoundRole).isNull();
    }
}
