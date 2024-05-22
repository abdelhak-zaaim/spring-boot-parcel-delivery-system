
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 18:13
 *  * @modified : 23/04/2024, 18:13
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.entity;

import com.fsdm.pfe.delix.converters.ParcelLocationConverter;
import com.fsdm.pfe.delix.model.ParcelLocation;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class ParcelHistory {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Parcel idParcel;

    // the status of the parcel as a string
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ParcelTracking;

    @Convert(converter = ParcelLocationConverter.class)
    private ParcelLocation currentLocation;

    @PrePersist
    protected void onCreated() {
        this.ParcelTracking = new Date();

    }


}
