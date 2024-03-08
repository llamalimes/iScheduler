//package org.jonlima.iScheduler.deprecated;
//
//import org.jonlima.iScheduler.model.User;
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
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }
//
//    //READ
//    public List<User> getAllUsers(){
//        return userRepository.findAll();
//    }
//    public Optional<User> getUserById(long id){
//        return userRepository.findById(id);
//    }
//    //Authenticate user
//    public boolean authenticate(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        if (user != null && user.getPassword().equals(password)) {
//            return true;
//        }
//        return false;
//    }
//    //UPDATE
//    public User updateUser(Long id, User user) {
//        Optional<User> existingUserOptional = getUserById(id);
//        if (existingUserOptional.isPresent()) {
//            User existingUser = existingUserOptional.get();
//            existingUser.setUsername(user.getUsername());
//            existingUser.setEmail(user.getEmail());
//            existingUser.setPassword(user.getPassword());
//            existingUser.setTimeZone(user.getTimeZone());
//            return userRepository.save(existingUser);
//        } else {
//            throw new IllegalArgumentException("User not found with id: " + id);
//        }
//    }
//
//    //DELETE
//    public void deleteUser(long id) {
//        userRepository.deleteById(id);
//    }
//}
