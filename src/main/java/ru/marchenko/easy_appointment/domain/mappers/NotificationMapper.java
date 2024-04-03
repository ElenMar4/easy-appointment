package ru.marchenko.easy_appointment.domain.mappers;

import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.Notification;

public interface NotificationMapper {
    Notification toModel(Appointment appointment);
}
