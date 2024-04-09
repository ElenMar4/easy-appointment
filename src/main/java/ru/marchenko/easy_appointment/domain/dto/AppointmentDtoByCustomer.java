package ru.marchenko.easy_appointment.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AppointmentDtoByCustomer {
    private long id;
    private String date;
    private String startTime;
    private String finishTime;
    private String entrepreneurName;
    private String entrepreneurPhone;
}
