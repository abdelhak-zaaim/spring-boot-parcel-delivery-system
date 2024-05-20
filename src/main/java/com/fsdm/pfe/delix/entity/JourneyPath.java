/*
 *
 *  * @project : DeliX
 *  * @created : 19/05/2024, 18:59
 *  * @modified : 19/05/2024, 18:59
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JourneyPath {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<JourneyStep> steps;
    private Date creationDate;
    private Date lastUpdateDate;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Parcel> parcels;

    @PrePersist
    protected void onCreated() {


        Date date = new Date();
        this.creationDate = date;
        this.lastUpdateDate = date;
    }
}