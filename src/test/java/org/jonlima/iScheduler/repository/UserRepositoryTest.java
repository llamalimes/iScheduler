package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private Users user1;
    private Users user2;

    @BeforeEach
    void setUp() {
        // Clean up the repository to ensure a fresh start
        userRepository.deleteAll();

        // Initialize and save the test users
        user1 = new Users();
        user1.setName("Test User One");
        user1.setEmail("testuserone@example.com");
        user1.setPassword("password123");
        userRepository.save(user1);

        user2 = new Users();
        user2.setName("Test User Two");
        user2.setEmail("testusertwo@example.com");
        user2.setPassword("password456");
        userRepository.save(user2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"testuserone@example.com", "testusertwo@example.com"})
    void testFindByEmailWithValidEmails(String email) {
        Users foundUser = userRepository.findByEmail(email);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo(email);
    }

    @Test
    void testFindUserByIdWithValidId() {
        Users foundUser = userRepository.findUserById(user1.getId());
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo(user1.getId());
    }
}
