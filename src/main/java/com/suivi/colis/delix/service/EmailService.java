/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 18:38
 *  * @modified : 01/05/2024, 18:38
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.service;

import com.suivi.colis.delix.entity.User;

import java.util.Optional;

public interface EmailService {
   /**
    * Service interface for {@link com.suivi.colis.delix.service.Impl.EmailServiceImpl}
    *
    *
    * */
   void sendSimpleMessage(String toEmail, String subject, String text);
   void sendSimpleMessageUsingTemplate(String toEmail, String subject, String ...templateModel);
   void sendEmailWithAttachment(String toEmail, String subject, String text, String pathToAttachment);

}
