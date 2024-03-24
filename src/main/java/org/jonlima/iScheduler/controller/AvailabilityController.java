package org.jonlima.iScheduler.controller;

import jakarta.validation.Valid;
import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.TimeBlock;
import org.jonlima.iScheduler.model.dto.AvailabilityForm;
import org.jonlima.iScheduler.model.Users;
import org.jonlima.iScheduler.model.dto.TimeBlockForm;
import org.jonlima.iScheduler.service.AvailabilityService;
import org.jonlima.iScheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;
    private final UserService userService;

    @Autowired
    public AvailabilityController(AvailabilityService availabilityService, UserService userService) {
        this.availabilityService = availabilityService;
        this.userService = userService;
    }

    // Endpoint to display the availability modification form
    @GetMapping("/modify")
    public String showAvailabilityForm(Model model) {
        AvailabilityForm availabilityForm = new AvailabilityForm();
        availabilityForm.setTimeBlocks(List.of(new TimeBlockForm()));

        model.addAttribute("availabilityForm", availabilityForm);

        // Add any additional data needed for the form (e.g., user information)
        return "availability-form";
    }
    // Endpoint to handle form submission and update availability
    @PostMapping("/modify")
    public String modifyAvailability(@ModelAttribute("availabilityForm") @Valid AvailabilityForm availabilityForm,
                                     BindingResult bindingResult,
                                     Principal principal) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form page
            return "availability-form";
        }

        // Retrieve the currently logged-in users
        String email = principal.getName();
        Users users = userService.findUserByEmail(email);

        // Convert the form data to an Availability entity
        Availability availability = availabilityService.convertToAvailability(availabilityForm, users);

        // Save or update the availability
        availabilityService.saveAvailability(availability);

        // Redirect to a confirmation page
        return "redirect:/availability/confirmation";
    }
    @GetMapping("/compare-availabilities")
    public String compareAvailabilities(@RequestParam Long userId1, @RequestParam Long userId2, Model model) {
        Users users1 = userService.findById(userId1);
        Users users2 = userService.findById(userId2);
        System.out.println(users1);
        System.out.println(users2);
        TimeBlock commonAvailability = availabilityService.findCommonAvailability(users1, users2);
        System.out.println(commonAvailability);
        model.addAttribute("commonAvailability", commonAvailability);
        return "availability-comparison"; // Name of the Thymeleaf template to display the result
    }


    // Endpoint to display a confirmation page
    @GetMapping("/confirmation")
    public String showConfirmationPage() {
        return "confirmation-page"; // The name of the Thymeleaf template for the confirmation page
    }
}
