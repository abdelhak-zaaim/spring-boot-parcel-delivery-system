/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 21:44
 *  * @modified : 20/05/2024, 21:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pricing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double basePrice = 25.0;

    private double densityFactor = 0.5;

    private double distanceFactor = 1.0;

    private double insuranceCostFactor = 0.2;

}
