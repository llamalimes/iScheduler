package org.jonlima.iScheduler.service;
import org.jonlima.iScheduler.model.dto.UserDTO;
import org.jonlima.iScheduler.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDTO userDTO);
    User findUserByEmail(String email);
    List<UserDTO> findAllUsers();
}
