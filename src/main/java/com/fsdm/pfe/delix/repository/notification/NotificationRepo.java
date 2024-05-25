/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 17:50
 *  * @modified : 16/05/2024, 17:50
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.repository.notification;

import com.fsdm.pfe.delix.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
}