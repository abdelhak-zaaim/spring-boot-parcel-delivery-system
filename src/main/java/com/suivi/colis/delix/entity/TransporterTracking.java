
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 20:27
 *  * @modified : 23/04/2024, 20:27
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Transporter asigned_transporter;

}