package ru.marchenko.easy_appointment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import ru.marchenko.easy_appointment.domain.dto.CustomerDto;
import ru.marchenko.easy_appointment.exceptions.UserAlreadyExistsException;
import ru.marchenko.easy_appointment.services.CustomerService;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final CustomerService customerService;

    @GetMapping("/registration")
    public String GetRegistration(Model model){
        model.addAttribute("customerDto", new CustomerDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(@ModelAttribute CustomerDto customerDto) throws UserAlreadyExistsException {
        customerService.create(customerDto);
        return "redirect:/login";
    }

}
