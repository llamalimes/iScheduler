package org.jonlima.iScheduler.controller;

import jakarta.validation.Valid;
import org.jonlima.iScheduler.model.dto.UserDTO;
import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.User;
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
public class UserController {
    private UserService userService;
    private FriendshipService friendshipService;
    //@Autowired
    private AvailabilityService availabilityService;

    @Autowired
    public UserController(UserService userService, FriendshipService friendshipService, AvailabilityService availabilityService) {
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
        model.addAttribute("user", user);

        return "register";
    }

    //handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDTO userDto, BindingResult result, Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
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
        User user = userService.findUserByEmail(email);
        model.addAttribute("user", user);
        model.addAttribute("name", user.getName());

        List<User> friends = friendshipService.findFriendsByUserId(user.getId());
        model.addAttribute("friends", friends);

        // Retrieve availability of the logged-in user grouped by day
        //Map<DayOfWeek, List<Availability>> availabilitiesByDay = availabilityService.findAvailabilitiesGroupedByDay(user);
        //model.addAttribute("availabilitiesByDay", availabilitiesByDay);

        // Retrieve availability of the logged-in user
        List<Availability> availabilities = availabilityService.findAvailabilitiesByUser(user);
        Map<DayOfWeek, List<Availability>> availabilitiesByDay = availabilities.stream()
                .collect(Collectors.groupingBy(Availability::getDayOfWeek));
        model.addAttribute("availabilitiesByDay", availabilitiesByDay);

        return "users";
    }


}

