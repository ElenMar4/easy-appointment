package ru.marchenko.easy_appointment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("/easy-appointment")
    public String homePage (){
        return "home";
    }
}
