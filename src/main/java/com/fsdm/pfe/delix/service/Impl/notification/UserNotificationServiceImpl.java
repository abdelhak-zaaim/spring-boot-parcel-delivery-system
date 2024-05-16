/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 18:24
 *  * @modified : 16/05/2024, 18:24
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl.notification;

import com.fsdm.pfe.delix.entity.User;
import com.fsdm.pfe.delix.service.notification.UserNotificationService;
import org.springframework.stereotype.Service;

@Service
public class UserNotificationServiceImpl implements UserNotificationService {

    @Override
    public void sendNotificationToUser(String title, String message, User user) {

    }

    @Override
    public void sendNotificationToAll(String title, String message) {

    }
}
