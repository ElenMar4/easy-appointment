package ru.marchenko.easy_appointment.services.impl;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.marchenko.easy_appointment.domain.Notification;
import ru.marchenko.easy_appointment.domain.Status;
import ru.marchenko.easy_appointment.services.EmailService;
import ru.marchenko.easy_appointment.services.GenerateNotificationService;
import ru.marchenko.easy_appointment.services.NotificationService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class GenerateNotificationServiceImpl implements GenerateNotificationService {

    private final NotificationService notificationService;

    private final EmailService emailService;

    @Scheduled(cron = "0 0/1 * * * *")
    public void reportCurrentTime() throws MessagingException {
        List<Notification> notifications = notificationService.getAllForSend(LocalDateTime.now(), Status.UNSENT);
        for (Notification notification : notifications){
            String subject = "Уведомление о предстоящей записи";
            String message = String.format("Напоминаем, что вы записаны к %s: %s",
                        notification.getAppointment().getEntrepreneur().getName(),
                        notification.getDate_time().plusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm")));
            emailService.sendMessage(notification, subject, message);
            notification.setStatus(Status.SENT);
            notificationService.update(notification);
        }
    }
}
