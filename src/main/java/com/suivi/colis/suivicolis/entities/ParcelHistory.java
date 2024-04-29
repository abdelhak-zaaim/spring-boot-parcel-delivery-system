
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:13
 *  * @modified : 23/04/2024, 18:13
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.entities;

import com.suivi.colis.suivicolis.entities.converters.ParcelLocationConverter;
import com.suivi.colis.suivicolis.models.ParcelLocation;
import com.suivi.colis.suivicolis.utils.helpers.DateUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class ParcelHistory {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idParcel", referencedColumnName = "id")
    private Parcel idParcel;

    // the status of the parcel as a string
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ParcelTracking;

    @Convert(converter = ParcelLocationConverter.class)
    private ParcelLocation currentLocation;

    @PrePersist
    protected void onCreated() {
        this.ParcelTracking = DateUtils.getCurrentDateWithSpecifiedTimeZone();

    }


}
