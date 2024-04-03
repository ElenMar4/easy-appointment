package ru.marchenko.easy_appointment.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.marchenko.easy_appointment.domain.Appointment;
import ru.marchenko.easy_appointment.domain.Notification;
import ru.marchenko.easy_appointment.domain.Status;
import ru.marchenko.easy_appointment.domain.mappers.NotificationMapper;
import ru.marchenko.easy_appointment.repositories.NotificationRepository;
import ru.marchenko.easy_appointment.services.NotificationService;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @Override
    public void create(Appointment appointment) {
        Notification notification = notificationRepository.findByAppointment(appointment);
        if (notification == null){
            notificationRepository.save(notificationMapper.toModel(appointment));
        }
    }

    @Override
    public List<Notification> getAllForSend(LocalDateTime dateTime, Status status) {
        return notificationRepository.getAllByStatusAndDateTimeLessThan(Status.UNSENT, dateTime);
    }

    @Override
    public void delete(Appointment appointment) {
        notificationRepository.deleteByAppointment(appointment);
    }

    @Override
    public void update(Notification notification) {
        notificationRepository.save(notification);
    }
}
