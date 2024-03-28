package ru.marchenko.easy_appointment.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.marchenko.easy_appointment.domain.dto.*;
import ru.marchenko.easy_appointment.services.AppointmentService;
import ru.marchenko.easy_appointment.parsers.ScheduleParser;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EntrepreneurController {

    private final ScheduleParser scheduleParser;
    private final AppointmentService appointmentService;

    @GetMapping("/entrepreneur/{entrepreneur_id}/account")
    public String getAccountPage(Model model, @PathVariable long entrepreneur_id){
        List<String> week = scheduleParser.buildWeek(LocalDate.now());
        model.addAttribute("week", week);
        model.addAttribute("id", entrepreneur_id);
        return "entrepreneur/account";
    }

    @GetMapping("/entrepreneur/{entrepreneur_id}/{date}")
    public String getDayPage(@PathVariable long entrepreneur_id,
                             @PathVariable String date, Model model){
        List<ShortAppointmentDto> dtoList = appointmentService.getByDate(entrepreneur_id, date);
        model.addAttribute("id", entrepreneur_id);
        model.addAttribute("date", date);
        if (dtoList == null){
           model.addAttribute("dto", scheduleParser.buildScheduleByDay());
           return "entrepreneur/create-schedule";
        }
        model.addAttribute("appointments", dtoList);
        return "entrepreneur/schedule";
    }

    @PostMapping("/entrepreneur/{entrepreneur_id}/{date}/create-schedule")
    public String createNewScheduleByDay(@PathVariable long entrepreneur_id,
                                         @PathVariable String date,
                                         @RequestParam List<String> selectedTimeSlots){
        appointmentService.saveAllOpenAppointments(scheduleParser.generateCurrentSchedule(date, selectedTimeSlots, entrepreneur_id));
        return "redirect:/entrepreneur/{entrepreneur_id}/{date}";
    }

    @GetMapping("/entrepreneur/{entrepreneur_id}/appointment/{appId}")
    public String getAppointmentsDetailsPage(Model model, @PathVariable long entrepreneur_id, @PathVariable long appId){
        AppointmentDto dto = appointmentService.getById(appId);
        model.addAttribute("id", entrepreneur_id);
        model.addAttribute("dto", dto);
        return "entrepreneur/detailsApp";
    }

    @PostMapping("/entrepreneur/{entrepreneur_id}/{date}")
    public String deleteDaySchedule(@PathVariable long entrepreneur_id, @PathVariable String date){
        appointmentService.deleteAllByDate(date, entrepreneur_id);
        return "redirect:/entrepreneur/{entrepreneur_id}/account";
    }
}

