package org.jonlima.iScheduler.controller;

import org.jonlima.iScheduler.dto.EventDTO;
import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.service.EventService;
import org.jonlima.iScheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;

@Controller
public class EventController {

    private EventService eventService;
    @Autowired
    private UserService userService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @PostMapping("/createEvent")
    public String createEvent(@ModelAttribute("eventForm") EventDTO eventDTO, Principal principal) throws GeneralSecurityException, IOException {
        User user = userService.findUserByEmail(principal.getName());
        String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS_PATH");
        eventService.createEvent(eventDTO, user, credentialsPath);
        return "redirect:/users"; // Redirect to the home page
    }


}
