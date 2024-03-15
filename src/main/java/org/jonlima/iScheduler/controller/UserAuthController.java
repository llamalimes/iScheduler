package org.jonlima.iScheduler.controller;

import jakarta.validation.Valid;
import org.jonlima.iScheduler.dto.UserDTO;
import org.jonlima.iScheduler.model.User;
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
import java.util.List;
import java.util.TimeZone;

@Controller
public class UserAuthController {

    private UserService userService;
    private FriendshipService friendshipService;

    @Autowired
    public UserAuthController(UserService userService, FriendshipService friendshipService) {
        this.userService = userService;
        this.friendshipService = friendshipService;
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
    @ModelAttribute("timeZones")
    public String[] getTimeZones() {
        return TimeZone.getAvailableIDs();
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
    public String students(Model model, Principal principal){

        String email = principal.getName();
        User user = userService.findUserByEmail(email);
        model.addAttribute("name", user.getName());

        List<User> friends = friendshipService.findFriendsByUserId(user.getId());
        //model.addAttribute("username", user.getName());
        model.addAttribute("friends", friends);

        return "users";
    }

}

