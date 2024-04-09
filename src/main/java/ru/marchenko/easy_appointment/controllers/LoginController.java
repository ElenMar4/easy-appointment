package ru.marchenko.easy_appointment.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.marchenko.easy_appointment.domain.Customer;
import ru.marchenko.easy_appointment.domain.Entrepreneur;

import ru.marchenko.easy_appointment.domain.Role;
import ru.marchenko.easy_appointment.domain.User;
import ru.marchenko.easy_appointment.services.CustomerService;
import ru.marchenko.easy_appointment.services.EntrepreneurService;
import ru.marchenko.easy_appointment.services.impl.UserServiceImpl;

import java.security.Principal;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserServiceImpl userService;
    private final CustomerService customerService;
    private final EntrepreneurService entrepreneurService;

    @GetMapping("/login")
    public String get() {
        return "login";
    }

    @GetMapping("/home")
    public String getHome(Principal principal){
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);

            if (user != null) {
                Role role = user.getRole().stream().findFirst().get();
                if(user.getRole().equals(Collections.singleton(Role.CUSTOMER))){
                    Customer customer = customerService.getByUsername(user.getUsername());
                    String href = "/customer/" + customer.getId() + "/account";
                    return "redirect:" + href;
                }
                if(user.getRole().equals(Collections.singleton(Role.ENTREPRENEUR))){
                    Entrepreneur entrepreneur = entrepreneurService.getByUsername(user.getUsername());
                    String href = "/entrepreneur/" + entrepreneur.getId() + "/account";
                    return "redirect:" + href;
                }
                if(user.getRole().equals(Collections.singleton(Role.ADMIN))){
                    String href = "/admin";
                    return "redirect:" + href;
                }
            }
        }
        return "login";
    }
}
