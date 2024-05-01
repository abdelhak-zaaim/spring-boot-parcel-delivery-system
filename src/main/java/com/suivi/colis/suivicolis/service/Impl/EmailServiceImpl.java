/*
 * **
 *  * @project : SuiviColis
 *  * @created : 01/05/2024, 18:39
 *  * @modified : 01/05/2024, 18:39
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service.Impl;

import com.suivi.colis.suivicolis.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
    @Override
    public void sendSimpleMessage(String toEmail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }


    @Override
    public void sendSimpleMessageUsingTemplate(String toEmail, String subject, String... templateModel) {

    }
}
