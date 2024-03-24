package org.jonlima.iScheduler.service;

import org.jonlima.iScheduler.model.Role;
import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.model.dto.UserDTO;
import org.jonlima.iScheduler.repository.RoleRepository;
import org.jonlima.iScheduler.repository.UserRepository;
import org.jonlima.iScheduler.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;


class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private AvailabilityService availabilityService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findUserByEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        when(userRepository.findByEmail("test@example.com")).thenReturn(user);

        User result = userService.findUserByEmail("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }
    @Test
    void saveUser() {
        UserDTO userDto = new UserDTO();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("password");

        User expectedUser = new User();
        expectedUser.setName("John Doe");
        expectedUser.setEmail("john.doe@example.com");
        expectedUser.setPassword("encodedPassword");

        Role role = new Role();
        role.setName("ROLE_ADMIN");

        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(role);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(expectedUser);

        userService.saveUser(userDto);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals("John Doe", capturedUser.getName());
        assertEquals("john.doe@example.com", capturedUser.getEmail());
        assertEquals("encodedPassword", capturedUser.getPassword());
    }

}
