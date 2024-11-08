package com.challenge.sistema_notificacoes.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.challenge.sistema_notificacoes.controller.dto.NotificationsSendDto;
import com.challenge.sistema_notificacoes.controller.dto.UsersDto;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendEmail(UsersDto receiverInfo, NotificationsSendDto emailInfo) {
        var message = new SimpleMailMessage();

        message.setFrom("dummytest@email.com");
        message.setTo(receiverInfo.userEmail());
        message.setSubject(emailInfo.title());
        message.setText(emailInfo.description());

        mailSender.send(message);
    }
}
