package ru.marchenko.easy_appointment.services;

import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.Notification;
import ru.marchenko.easy_appointment.domain.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationService {
    void create(Appointment appointment);
    List<Notification> getAllForSend(LocalDateTime dateTime, Status status);

    void delete(Appointment appointment);

    void update(Notification notification);
}
