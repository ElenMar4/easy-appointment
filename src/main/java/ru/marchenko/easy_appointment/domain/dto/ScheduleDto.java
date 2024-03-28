package ru.marchenko.easy_appointment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ScheduleDto {
    private Map<String, List<ShortAppointmentDto>> schedule;
}
