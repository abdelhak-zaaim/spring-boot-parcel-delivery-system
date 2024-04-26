/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:50
 *  * @modified : 26/04/2024, 01:24
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 19:44
 *  * @modified : 23/04/2024, 19:44
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models.entities;

import com.suivi.colis.suivicolis.models.LocationPoint;
import com.suivi.colis.suivicolis.utils.Helper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class DeliveryArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String areaName;

    @Column(nullable = false, unique = true)
    private String areaCode;

    @ElementCollection
    private List<LocationPoint> areaVertices;

    @ManyToOne
    private Admin createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date lastUpdateDate;

    @PrePersist
    protected void onCreated() {
        java.util.Date date = Helper.getCurrentDateWithSpecifiedTimeZone();
        this.creationDate = date;
        this.lastUpdateDate = date;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = Helper.getCurrentDateWithSpecifiedTimeZone();
    }

}