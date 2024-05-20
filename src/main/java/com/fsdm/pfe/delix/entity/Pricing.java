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

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pricing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private double basePrice = 25.0;
    private double densityFactor = 0.5;
    private double distanceFactor = 1.0;
    private double insuranceCostFactor = 0.2;

}
