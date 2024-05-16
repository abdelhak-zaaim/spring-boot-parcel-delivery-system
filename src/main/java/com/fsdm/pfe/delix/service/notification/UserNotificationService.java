/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 18:24
 *  * @modified : 16/05/2024, 18:24
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.notification;

public interface UserNotificationService {
    void sendNotificationToUser(String title, String message, String user);

    void sendNotificationToAll(String title, String message);
}
