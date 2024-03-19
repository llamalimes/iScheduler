//package org.jonlima.iScheduler.controller;
//
//import org.jonlima.iScheduler.model.Availability;
//import org.jonlima.iScheduler.model.User;
//import org.jonlima.iScheduler.service.AvailabilityService;
//import org.jonlima.iScheduler.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.security.Principal;
//
//@Controller
//@RequestMapping("/availability")
//public class AvailabilityController {
//
//    private AvailabilityService availabilityService;
//    private UserService userService;
//
//    @Autowired
//    public AvailabilityController(AvailabilityService availabilityService, UserService userService) {
//        this.availabilityService = availabilityService;
//        this.userService = userService;
//    }
//
//    @PostMapping("/save")
//    public String saveAvailability(@ModelAttribute("availability") Availability availability, Principal principal) {
//        String email = principal.getName();
//        User user = userService.findUserByEmail(email);
//        availability.setUser(user);
//        availabilityService.saveAvailability(availability);
//        return "redirect:/users?availabilitySaved";
//    }
//
//    @PostMapping("/saveBlocks")
//    public String saveAvailabilityBlocks(@ModelAttribute("availability") Availability availability, Principal principal) {
//        String email = principal.getName();
//        User user = userService.findUserByEmail(email);
//        availability.setUser(user);
//        availabilityService.saveAvailabilityBlocks(availability);
//        return "redirect:/users?availabilitySaved";
//    }
//}
