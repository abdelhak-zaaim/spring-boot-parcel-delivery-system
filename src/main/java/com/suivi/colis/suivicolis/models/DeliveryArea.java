/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 19:44
 *  * @modified : 23/04/2024, 19:44
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class DeliveryArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaName;

    private String areaCode;

    private Date creationDate;

    @ManyToOne
    private Admin createdBy;

}