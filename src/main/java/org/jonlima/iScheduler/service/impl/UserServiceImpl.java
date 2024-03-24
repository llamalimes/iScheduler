package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.dto.UserDTO;
import org.jonlima.iScheduler.model.Role;
import org.jonlima.iScheduler.model.Users;
import org.jonlima.iScheduler.repository.RoleRepository;
import org.jonlima.iScheduler.repository.UserRepository;
import org.jonlima.iScheduler.service.AvailabilityService;
import org.jonlima.iScheduler.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AvailabilityService availabilityService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AvailabilityService availabilityService, PasswordEncoder passwordEncoder){
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.availabilityService = availabilityService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDto) {
        Users users = new Users();

        users.setName(userDto.getFirstName() + " " + userDto.getLastName());
        users.setEmail(userDto.getEmail());

        // Encrypt the password using Spring Security
        users.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        users.setRoles(Arrays.asList(role));
        userRepository.save(users);

        //availabilityService.initializeClosedAvailability(users);
    }


    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public Users findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDTO> findAllUsers(){
        List<Users> users = userRepository.findAll();

        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public Users findById(Long id) {
        return userRepository.findUserById(id);
    }

    public UserDTO mapToUserDto(Users users){
        UserDTO userDTO = new UserDTO();

        String[] str = users.getName().split(" ");
        userDTO.setFirstName(str[0]);
        userDTO.setLastName(str[1]);
        userDTO.setEmail(users.getEmail());
        return userDTO;
    }
}
