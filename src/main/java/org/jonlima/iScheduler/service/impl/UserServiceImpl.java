package org.jonlima.iScheduler.service.impl;

import org.jonlima.iScheduler.model.dto.UserDTO;
import org.jonlima.iScheduler.model.Role;
import org.jonlima.iScheduler.model.User;
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
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AvailabilityService availabilityService;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AvailabilityService availabilityService, PasswordEncoder passwordEncoder){
        super();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.availabilityService = availabilityService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDTO userDto) {
        User user = new User();

        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        // Encrypt the password using Spring Security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);

        //availabilityService.initializeClosedAvailability(user);
    }


    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
//    @Override
//    public User findUserByUsername(String username){
//        return userRepository.findByUsername(username);
//    }
    @Override
    public List<UserDTO> findAllUsers(){
        List<User> users = userRepository.findAll();

        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {
        return userRepository.findUserById(id);
    }

    public UserDTO mapToUserDto(User user){
        UserDTO userDTO = new UserDTO();

        String[] str = user.getName().split(" ");
        userDTO.setFirstName(str[0]);
        userDTO.setLastName(str[1]);
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
