/*
 *
 *  * @project : DeliX
 *  * @created : 19/05/2024, 18:52
 *  * @modified : 19/05/2024, 18:52
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Transporter transporter;

    @OneToMany
    private List<JourneyPath> jumpPaths;

    private Date startTime;
    private Date endTime;

    @ManyToOne
    private Agency departureAgency;
    @ManyToOne
    private Agency arrivalAgency;

   @ManyToOne
   private JourneyPath journeyPath;

   @OneToOne
   private JourneyPlanPath journeyPlanPath;

   private Date creationDate;
   private Date lastUpdateDate;

   @PrePersist
   protected void onCreated() {


      Date date = new Date();
      this.creationDate = date;
      this.lastUpdateDate = date;
   }
}