package ru.marchenko.easy_appointment.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.marchenko.easy_appointment.domain.Notification;
import ru.marchenko.easy_appointment.services.EmailService;

@Service
@RequiredArgsConstructor
@Data
public class EmailServiceImpl implements EmailService {

//    @Value("${notification.host_name}")
//    private String hostName;
//
//    @Value("${notification.host_port}")
//    private String hostPort;
    private final JavaMailSender mailSender;
    private static String FROM_EMAIL = "easy_appointment@example.com";

    @Override
    public void sendMessage(Notification notification, String subject, String message) throws MessagingException {

//            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//            mailSender.setHost(hostName);
//            mailSender.setPort(Integer.parseInt(hostPort));
//            mailSender.setUsername("");
//            mailSender.setPassword("");
//
//            Properties props = mailSender.getJavaMailProperties();
//            props.put("mail.transport.protocol", "smtp");

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(FROM_EMAIL);
            mimeMessageHelper.setText(message, false);
            mimeMessageHelper.setTo(notification.getAppointment().getCustomer().getUser().getUsername());
            mimeMessageHelper.setSubject(subject);
            mailSender.send(mimeMessage);
    }
}
