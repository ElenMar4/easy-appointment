package ru.marchenko.easy_appointment.parsers;

import ru.marchenko.easy_appointment.domain.dto.ScheduleDto;
import ru.marchenko.easy_appointment.domain.dto.ShortAppointmentDto;
import ru.marchenko.easy_appointment.domain.dto.TimeSlotsDto;
import ru.marchenko.easy_appointment.domain.dto.OpenAppointmentDto;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleParser {
    List<String> buildWeek(LocalDate startDate);

    List<OpenAppointmentDto> generateCurrentSchedule(String date, List<String> selectedCheckBox, long entrepreneur_id);

    TimeSlotsDto buildScheduleByDay();

    ScheduleDto buildScheduleWithOpenedAppointment(List<ShortAppointmentDto> dtoList);
}
