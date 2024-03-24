//package org.jonlima.iScheduler.controller;
//
//import org.jonlima.iScheduler.model.Users;
//import org.jonlima.iScheduler.repository.UserRepository;
//import org.jonlima.iScheduler.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    public List<Users> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Users> getUserById(@PathVariable Long id) {
//        Optional<Users> user = userRepository.findById(id);
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<Users> createUser(@RequestBody Users user) {
//        Users savedUser = userRepository.save(user);
//        return ResponseEntity.created(URI.create("/api/users/" + savedUser.getId())).body(savedUser);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody Users user) {
//        if (!userRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        user.setId(id);
//        Users savedUser = userRepository.save(user);
//        return ResponseEntity.ok(savedUser);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        if (!userRepository.existsById(id)) {
//            return ResponseEntity.notFound().build();
//        }
//        userRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//}