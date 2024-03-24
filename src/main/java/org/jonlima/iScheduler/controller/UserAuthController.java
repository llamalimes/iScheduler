package org.jonlima.iScheduler.controller;

import jakarta.validation.Valid;
import org.jonlima.iScheduler.model.Users;
import org.jonlima.iScheduler.model.dto.UserDTO;
import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.service.AvailabilityService;
import org.jonlima.iScheduler.service.FriendshipService;
import org.jonlima.iScheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;

import java.security.Principal;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserAuthController {
    private UserService userService;
    private FriendshipService friendshipService;
    //@Autowired
    private AvailabilityService availabilityService;

    @Autowired
    public UserAuthController(UserService userService, FriendshipService friendshipService, AvailabilityService availabilityService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
        this.availabilityService = availabilityService;
    }
    //handler method to handle the home (index.html is home) page request

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    //handler method handles the login request
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //handler method to handle the user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        //create a model object to store form data
        UserDTO user = new UserDTO();
        model.addAttribute("users", user);

        return "register";
    }

    //handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDto, BindingResult result, Model model){
        Users existingUsers = userService.findUserByEmail(userDto.getEmail());

        if (existingUsers != null && existingUsers.getEmail() != null && !existingUsers.getEmail().isEmpty()){
            result.rejectValue("email", null, "There is already an account registered with the same email");
        }

        if (result.hasErrors()){
            model.addAttribute("user", userDto);

            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    //handler method is used to handle a list of students
    @GetMapping("/users")
    public String users(Model model, Principal principal){

        String email = principal.getName();
        Users users = userService.findUserByEmail(email);
        model.addAttribute("user", users);
        model.addAttribute("name", users.getName());

        List<Users> friends = friendshipService.findFriendsByUserId(users.getId());
        model.addAttribute("friends", friends);

        // Retrieve availability of the logged-in users grouped by day
        //Map<DayOfWeek, List<Availability>> availabilitiesByDay = availabilityService.findAvailabilitiesGroupedByDay(users);
        //model.addAttribute("availabilitiesByDay", availabilitiesByDay);

        // Retrieve availability of the logged-in users
        List<Availability> availabilities = availabilityService.findAvailabilitiesByUser(users);
        Map<DayOfWeek, List<Availability>> availabilitiesByDay = availabilities.stream()
                .collect(Collectors.groupingBy(Availability::getDayOfWeek));
        model.addAttribute("availabilitiesByDay", availabilitiesByDay);

        return "users";
    }


}

