/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 19:44
 *  * @modified : 23/04/2024, 19:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsdm.pfe.delix.converters.ListLocationPointListConverter;
import com.fsdm.pfe.delix.model.MapsLocationPoint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class DeliveryArea {

    @ManyToMany
    @NotNull
    Collection<Area> areas;

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
    private Admin createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(updatable = false)
    private java.util.Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private java.util.Date lastUpdateDate;

    @PrePersist
    protected void onCreated() {
        java.util.Date date = new Date();
        this.creationDate = date;
        this.lastUpdateDate = date;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = new Date();
    }


}