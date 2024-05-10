/*
 *
 *  * @project : DeliX
 *  * @created : 10/05/2024, 18:23
 *  * @modified : 10/05/2024, 18:23
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private User user;

    private Date creationDate;


    @PrePersist
    public void prePersist() {
        creationDate = new Date();
    }

}