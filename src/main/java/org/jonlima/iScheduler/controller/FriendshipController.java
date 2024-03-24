package org.jonlima.iScheduler.controller;

import org.jonlima.iScheduler.model.Users;
import org.jonlima.iScheduler.service.FriendshipService;
import org.jonlima.iScheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class FriendshipController {

    @Autowired
    private UserService userService;
    @Autowired
    private FriendshipService friendshipService;

    @PostMapping("/add-friend")
    public String addFriend(@RequestParam("friendEmail") String friendEmail, Principal principal, RedirectAttributes redirectAttributes) {
        String email = principal.getName(); // Get the username of the currently logged in users
        Users users = userService.findUserByEmail(email);

        Users friend = userService.findUserByEmail(friendEmail);

        if (friend != null) {
            friendshipService.addFriend(users, friend);
        } else {
            redirectAttributes.addFlashAttribute("error", "Users with that email not found");
        }

        return "redirect:/users";
    }

    @PostMapping("/remove-friend")
    public String removeFriend(@RequestParam("friendEmail") String friendEmail, Principal principal) {
    //public String removeFriend(Principal principal, Users friend) {
        String email = principal.getName(); // Get the username of the currently logged in users
        Users users = userService.findUserByEmail(email);
        Users friendFromDb = userService.findUserByEmail(friendEmail);

        if (users != null && friendFromDb != null) {
            System.out.println("INSIDE IF: " + friendFromDb);
            friendshipService.removeFriend(users, friendFromDb);
        }

        return "redirect:/users";
    }

}
