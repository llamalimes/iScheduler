//package org.jonlima.iScheduler.deprecated;
//
//import org.jonlima.iScheduler.model.Users;
//import org.jonlima.iScheduler.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserServiceOLD {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository){
//        this.userRepository = userRepository;
//    }
//
//    //CREATE
//    public Users createUser(Users user) {
//        return userRepository.save(user);
//    }
//
//    //READ
//    public List<Users> getAllUsers(){
//        return userRepository.findAll();
//    }
//    public Optional<Users> getUserById(long id){
//        return userRepository.findById(id);
//    }
//    //Authenticate user
//    public boolean authenticate(String username, String password) {
//        Users user = userRepository.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            return true;
//        }
//        return false;
//    }
//    //UPDATE
//    public Users updateUser(Long id, Users user) {
//        Optional<Users> existingUserOptional = getUserById(id);
//        if (existingUserOptional.isPresent()) {
//            Users existingUser = existingUserOptional.get();
//            existingUser.setUsername(user.getUsername());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setPassword(user.getPassword());
//            existingUser.setTimeZone(user.getTimeZone());
//            return userRepository.save(existingUser);
//        } else {
//            throw new IllegalArgumentException("Users not found with id: " + id);
//        }
//    }
//
//    //DELETE
//    public void deleteUser(long id) {
//        userRepository.deleteById(id);
//    }
//}
