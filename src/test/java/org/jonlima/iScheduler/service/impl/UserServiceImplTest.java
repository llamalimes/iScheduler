package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.Role;
import org.jonlima.iScheduler.model.Users;
import org.jonlima.iScheduler.model.dto.UserDTO;
import org.jonlima.iScheduler.repository.RoleRepository;
import org.jonlima.iScheduler.repository.UserRepository;
import org.jonlima.iScheduler.service.AvailabilityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

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

    @Captor
    private ArgumentCaptor<Users> usersCaptor;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
        userDTO.setFirstName("John");
        userDTO.setLastName("Doe");
        userDTO.setEmail("john.doe@example.com");
        userDTO.setPassword("password");

        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        when(roleRepository.findByName("ROLE_ADMIN")).thenReturn(adminRole);
    }

    @Test
    void testSaveUser() {
        userService.saveUser(userDTO);

        verify(userRepository).save(usersCaptor.capture());
        Users savedUser = usersCaptor.getValue();

        assertThat(savedUser.getName()).isEqualTo("John Doe");
        assertThat(savedUser.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(savedUser.getPassword()).isEqualTo("encodedPassword");
        assertThat(savedUser.getRoles()).hasSize(1);
        assertThat(savedUser.getRoles().getFirst().getName()).isEqualTo("ROLE_ADMIN");
    }
}
