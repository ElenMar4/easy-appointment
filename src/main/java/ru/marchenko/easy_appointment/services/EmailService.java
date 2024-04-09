package ru.marchenko.easy_appointment.services;

import jakarta.mail.MessagingException;
import ru.marchenko.easy_appointment.domain.Notification;

public interface EmailService {

    void sendMessage(Notification notification, String subject, String message) throws MessagingException;
}
