package org.jonlima.iScheduler.repository;

import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @ParameterizedTest
    @ValueSource(strings = {"test@example.com", "another@test.com"})
    public void testFindByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        userRepository.save(user);

        User foundUser = userRepository.findByEmail(email);
        assertNotNull(foundUser);
    }
}
