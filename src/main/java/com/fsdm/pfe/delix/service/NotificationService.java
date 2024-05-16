
/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 17:41
 *  * @modified : 16/05/2024, 17:41
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.notification.Notification;

public interface NotificationService {

    Notification getNotificationById(Long id);

    void deleteNotification(Long id);

    void deleteAllNotifications();

    Notification updateNotification(Notification notification);

    Notification createNotification(Notification notification);
}
