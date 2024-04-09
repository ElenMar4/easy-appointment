package ru.marchenko.easy_appointment.domain.mappers;

import org.springframework.stereotype.Component;
import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.Notification;
import ru.marchenko.easy_appointment.domain.Status;

@Component
public class NotificationMapperImpl implements NotificationMapper{

    @Override
    public Notification toModel(Appointment appointment) {
        return new Notification(null,
                appointment.getDateTime().minusDays(1),
                appointment,
                Status.UNSENT);
    }
}
