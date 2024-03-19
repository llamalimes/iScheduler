package org.jonlima.iScheduler.controller;

import jakarta.validation.Valid;
import org.jonlima.iScheduler.model.Availability;
import org.jonlima.iScheduler.model.dto.AvailabilityForm;
import org.jonlima.iScheduler.model.User;
import org.jonlima.iScheduler.service.AvailabilityService;
import org.jonlima.iScheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
        model.addAttribute("availabilityForm", new AvailabilityForm());
        // Add any additional data needed for the form (e.g., user information)
        return "availability-form"; // The name of the Thymeleaf template for the form
    }

    // Endpoint to handle form submission and update availability
    // Endpoint to handle form submission and update availability
    @PostMapping("/modify")
    public String modifyAvailability(@ModelAttribute("availabilityForm") @Valid AvailabilityForm availabilityForm,
                                     BindingResult bindingResult,
                                     Principal principal) {
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the form page
            return "availability-form";
        }

        // Retrieve the currently logged-in user
        String email = principal.getName();
        User user = userService.findUserByEmail(email);

        // Convert the form data to an Availability entity
        Availability availability = availabilityService.convertToAvailability(availabilityForm, user);

        // Save or update the availability
        availabilityService.saveAvailability(availability);

        // Redirect to a confirmation page
        return "redirect:/availability/confirmation";
    }


    // Endpoint to display a confirmation page
    @GetMapping("/confirmation")
    public String showConfirmationPage() {
        return "confirmation-page"; // The name of the Thymeleaf template for the confirmation page
    }
}
