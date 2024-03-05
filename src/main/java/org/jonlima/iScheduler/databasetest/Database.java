package org.jonlima.iScheduler.databasetest;

import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.repository.UserRepository;
import org.jonlima.iScheduler.service.UserService;

public class Database {
    public static void main(String[] args) {
        User u1 = new User();
        u1.setUsername("john_doe");
        u1.setEmail("john.doe@example.com");
        u1.setPassword("password123");
        u1.setTimeZone("UTC-5");

        User u2 = new User();
        u2.setUsername("jane_smith");
        u2.setEmail("jane.smith@example.com");
        u2.setPassword("password456");
        u2.setTimeZone("UTC-7");

        User u3 = new User();
        u3.setUsername("alice_johnson");
        u3.setEmail("alice.johnson@example.com");
        u3.setPassword("password789");
        u3.setTimeZone("UTC-6");

        User u4 = new User();
        u4.setUsername("bob_jones");
        u4.setEmail("bob.jones@example.com");
        u4.setPassword("password321");
        u4.setTimeZone("UTC-8");

        User u5 = new User();
        u5.setUsername("sara_smith");
        u5.setEmail("sara.smith@example.com");
        u5.setPassword("password654");
        u5.setTimeZone("UTC-5");

        User u6 = new User();
        u6.setUsername("mike_davis");
        u6.setEmail("mike.davis@example.com");
        u6.setPassword("password987");
        u6.setTimeZone("UTC-6");

        User u7 = new User();
        u7.setUsername("emily_wilson");
        u7.setEmail("emily.wilson@example.com");
        u7.setPassword("password654");
        u7.setTimeZone("UTC-7");

        User u8 = new User();
        u8.setUsername("david_johnson");
        u8.setEmail("david.johnson@example.com");
        u8.setPassword("password321");
        u8.setTimeZone("UTC-8");

        User u9 = new User();
        u9.setUsername("amy_brown");
        u9.setEmail("amy.brown@example.com");
        u9.setPassword("password987");
        u9.setTimeZone("UTC-5");

        User u10 = new User();
        u10.setUsername("tom_wilson");
        u10.setEmail("tom.wilson@example.com");
        u10.setPassword("password123");
        u10.setTimeZone("UTC-6");


    }
}
