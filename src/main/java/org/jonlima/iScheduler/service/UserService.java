package org.jonlima.iScheduler.service;
import org.jonlima.iScheduler.model.Users;
import org.jonlima.iScheduler.model.dto.UserDTO;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    Users findUserByEmail(String email);
    List<UserDTO> findAllUsers();

    Users findById(Long id);
}
