
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 19:44
 *  * @modified : 23/04/2024, 19:44
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.entities;

import com.suivi.colis.suivicolis.entities.converters.ListLocationPointListConverter;
import com.suivi.colis.suivicolis.models.MapsLocationPoint;
import com.suivi.colis.suivicolis.utils.helpers.DateUtils;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class DeliveryArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String areaName;

    @Column(nullable = false, unique = true)
    private String areaCode;

    @Convert(converter = ListLocationPointListConverter.class)
    private List<MapsLocationPoint> areaVertices;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private AdminEmployee createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date lastUpdateDate;

    @PrePersist
    protected void onCreated() {
        java.util.Date date = DateUtils.getCurrentDateWithSpecifiedTimeZone();
        this.creationDate = date;
        this.lastUpdateDate = date;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = DateUtils.getCurrentDateWithSpecifiedTimeZone();
    }


}