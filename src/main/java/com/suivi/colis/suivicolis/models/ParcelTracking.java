/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:13
 *  * @modified : 23/04/2024, 18:13
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data

public class ParcelTracking {

    @Id
    private Long id;

    // the parcel that this track belongs to
    @ManyToOne
    @JoinColumn(name = "idParcel", referencedColumnName = "id")
    private Parcel idParcel;

    // the status of the parcel as a string
    private String status;
    private Date date;




}
