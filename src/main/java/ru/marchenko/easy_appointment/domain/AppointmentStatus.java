package ru.marchenko.easy_appointment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppointmentStatus {
    OPENED("opened"),
    CLOSED("closed");

    private final String status;
}
