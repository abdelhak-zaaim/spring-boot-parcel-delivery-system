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
/**
 * This class represents the pricing model of the application
 * It contains the base price, the density factor, the distance factor and the insurance cost factor
 * The price of a delivery is calculated as follows:
 * price = basePrice + (densityFactor * weight) + (distanceFactor * distance) + (insuranceCostFactor * insuranceCost)
 * where weight is the weight of the delivery, distance is the distance between the sender and the receiver and insuranceCost is the cost of the insurance
 * The default values are:
 * basePrice = 25.0
 * densityFactor = 0.5
 * distanceFactor = 1.0
 * insuranceCostFactor = 0.2
 * The admin can change these values
 * @see com.fsdm.pfe.delix.service.PricingService
 * @see com.fsdm.pfe.delix.controller.PricingController
 * @see com.fsdm.pfe.delix.service.Impl.PricingServiceImpl
 * @see com.fsdm.pfe.delix.controller.Impl.PricingControllerImpl
 * @see com.fsdm.pfe.delix.entity.Delivery

 */
public class Pricing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // this is the base price of a delivery en Mad
    private double basePrice = 25.0;

    private double densityFactor = 0.5;
    // this is the distance factor
    private double distanceFactor = 1.0;
    // this is the insurance cost factor
    private double insuranceCostFactor = 0.2;


}
