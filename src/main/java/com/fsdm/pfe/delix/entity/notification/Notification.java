/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 17:47
 *  * @modified : 16/05/2024, 17:47
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.entity.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String title;
    private String message;
    private String user;
    private Date date;

    public Notification(String title, String message, String user) {
        this.title = title;
        this.message = message;
        this.user = user;
    }


}
