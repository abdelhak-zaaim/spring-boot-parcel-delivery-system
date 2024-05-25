/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 18:39
 *  * @modified : 01/05/2024, 18:39
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.dto.email.ResetPasswordEmailTemplateDTO;
import com.fsdm.pfe.delix.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Map;
import java.util.Objects;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
    private final JavaMailSender emailSender;
    private final TemplateEngine templateEngine;

    public EmailServiceImpl(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }


    public void sendEmailWithTemplate(String toEmail, String subject, String templateName, Map<String, Object> variables) {


        Context context = new Context();
        context.setVariables(variables);
        try {
            String body = templateEngine.process(templateName, context);

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body, true);

            emailSender.send(message);
        } catch (Exception e) {
            System.out.println("Error occurred while sending email with template " + e.getMessage());
        }


    }


    @Override
    public void sendMessage(String toEmail, String subject, String text) {
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

    public void sendEmailWithTemplate(String toEmail, String subject, String templateName, ResetPasswordEmailTemplateDTO resetPasswordEmailTemplateDTO) {
        System.out.println("Sending email with template to " + resetPasswordEmailTemplateDTO.toString());
        // resetPasswordEmailTemplateDTO to map
        Map<String, Object> variables = Map.of(
                "name", resetPasswordEmailTemplateDTO.getName(),
                "actionUrl", resetPasswordEmailTemplateDTO.getActionUrl(),
                "operatingSystem", resetPasswordEmailTemplateDTO.getOperatingSystem(),
                "browserName", resetPasswordEmailTemplateDTO.getBrowserName()
        );

        Context context = new Context();
        context.setVariables(variables);
        try {
            String body = templateEngine.process(templateName, context);
            System.out.println(body);
            sendMessage(toEmail, subject, body);
        } catch (Exception e) {
            System.out.println("Error occurred while sending email with template " + e.getMessage());
        }


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
