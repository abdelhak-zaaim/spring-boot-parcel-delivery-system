/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 17:33
 *  * @modified : 16/05/2024, 17:33
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.entity.notification;

import com.fsdm.pfe.delix.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class FirebaseUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "messaging_key", nullable = false)
    private String messagingKey;


}