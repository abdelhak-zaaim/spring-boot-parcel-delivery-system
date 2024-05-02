/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 18:38
 *  * @modified : 01/05/2024, 18:38
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service;

public interface EmailService {
   void sendSimpleMessage(String toEmail, String subject, String text);
   void sendSimpleMessageUsingTemplate(String toEmail, String subject, String ...templateModel);
}
