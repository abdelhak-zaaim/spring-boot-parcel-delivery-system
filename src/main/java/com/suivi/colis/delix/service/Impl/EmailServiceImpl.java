/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 18:39
 *  * @modified : 01/05/2024, 18:39
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.service.Impl;

import com.suivi.colis.delix.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
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

        try {
            emailSender.send(message);
            log.debug("Email sent successfully");
        } catch (MailException e) {
            log.debug("Error occurred while sending email " + e.getMessage());
        }

    }

    @Override
    public void sendSimpleMessageUsingTemplate(String toEmail, String subject, String... templateModel) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(String.format(templateModel[0], templateModel[1]));
        emailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(String toEmail, String subject, String text, String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);

            emailSender.send(message);
            log.debug("Email sent successfully with attachment");
        } catch (MessagingException e) {
            log.debug("Error occurred while sending email with attachment " + e.getMessage());
        }
    }


}
