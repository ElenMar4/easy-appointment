package ru.marchenko.easy_appointment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class TimeSlotsDto {
    private List<String> timeSlots;
}
