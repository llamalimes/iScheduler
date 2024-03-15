package org.jonlima.iScheduler.controller;

import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.service.FriendshipService;
import org.jonlima.iScheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        String email = principal.getName(); // Get the username of the currently logged in user
        User user = userService.findUserByEmail(email);

        User friend = userService.findUserByEmail(friendEmail);

        if (friend != null) {
            friendshipService.addFriend(user, friend);
        } else {
            redirectAttributes.addFlashAttribute("error", "User with that email not found");
        }

        return "redirect:/users";
    }

    @PostMapping("/remove-friend")
    public String removeFriend(@RequestParam("friendEmail") String friendEmail, Principal principal) {
    //public String removeFriend(Principal principal, User friend) {
        String email = principal.getName(); // Get the username of the currently logged in user
        User user = userService.findUserByEmail(email);
        User friendFromDb = userService.findUserByEmail(friendEmail);

        if (user != null && friendFromDb != null) {
            System.out.println("INSIDE IF: " + friendFromDb);
            friendshipService.removeFriend(user, friendFromDb);
        }

        return "redirect:/users";
    }

}
