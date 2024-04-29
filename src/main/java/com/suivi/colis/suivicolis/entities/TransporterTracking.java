
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 20:27
 *  * @modified : 23/04/2024, 20:27
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class TransporterTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currentLocation;

    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;


    private double latitude;
    private double longitude;

    @ManyToOne
    private Transporter asigned_transporter;

}