
/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 17:43
 *  * @modified : 16/05/2024, 17:43
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.entity.notification.Notification;
import com.fsdm.pfe.delix.exception.personalizedexceptions.NotificationNotFoundException;
import com.fsdm.pfe.delix.repository.notification.NotificationRepo;
import com.fsdm.pfe.delix.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepo notificationRepo;

    public NotificationServiceImpl(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteNotification(Long id) {
        notificationRepo.deleteById(id);

    }

    @Override
    public void deleteAllNotifications() {
        notificationRepo.deleteAll();

    }

    @Override
    public Notification updateNotification(Notification notification) {
        if(notification.getId() == null || notification.getId() == 0 )
            throw new NotificationNotFoundException("Notification id is required");
        if (notificationRepo.findById(notification.getId()).isEmpty())
            throw new NotificationNotFoundException("Notification with id " + notification.getId() + " not found");
        return notificationRepo.save(notification);
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepo.save(notification);
    }
}
