package ru.marchenko.easy_appointment.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.marchenko.easy_appointment.domain.dto.*;
import ru.marchenko.easy_appointment.parsers.ScheduleParser;
import ru.marchenko.easy_appointment.services.AppointmentService;
import ru.marchenko.easy_appointment.services.CustomerService;
import ru.marchenko.easy_appointment.services.EntrepreneurService;

import java.util.List;

@Controller
@Data
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final AppointmentService appointmentService;
    private final EntrepreneurService entrepreneurService;
    private final ScheduleParser scheduleParser;

    @GetMapping("/customer/{customer_id}/account")
    public String getAccountPage(Model model, @PathVariable long customer_id) {
        List<AppointmentDtoByCustomer> appointments = appointmentService.getAllByCustomer(customer_id);
        List<EntrepreneurDtoByCustomer> entrepreneurs = entrepreneurService.getAllByCustomer();
        model.addAttribute("id", customer_id);
        model.addAttribute("appointments", appointments);
        model.addAttribute("entrepreneurs", entrepreneurs);
        return "customer/account";
    }

    @GetMapping("/customer/{customer_id}/{entrepreneur_id}/create")
    public String getCreateAppointmentPage(@PathVariable long customer_id,
                                           @PathVariable long entrepreneur_id,
                                           Model model) {
        List<ShortAppointmentDto> appointments = appointmentService.getAllByEntrepreneurAndStatus(entrepreneur_id);
        ScheduleDto schedules = scheduleParser.buildScheduleWithOpenedAppointment(appointments);
        model.addAttribute("appointments", appointments);
        model.addAttribute("id", customer_id);
        model.addAttribute("schedules", schedules);
        model.addAttribute("entrepreneur_id", entrepreneur_id);
        return "customer/appointment";
    }

    @PostMapping("/customer/{customer_id}/create")
    public String createAppointment(@PathVariable long customer_id,
                                    @RequestParam long appId) {
        appointmentService.save(appId, customer_id);
        return "redirect:/customer/{customer_id}/account";
    }

    @PostMapping("/customer/{customer_id}/cancel/{app_id}")
    public String cancelAppointment(@PathVariable long customer_id,
                                    @PathVariable long app_id) {
        appointmentService.cancelAppointment(app_id);
        return "redirect:/customer/{customer_id}/account";
    }
}
