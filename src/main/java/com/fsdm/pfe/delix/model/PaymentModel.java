/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 21:13
 *  * @modified : 20/05/2024, 21:13
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.model;

import lombok.*;

@Value
@Getter
@Setter
@Builder
public class PaymentModel {

   int totalCost;
   int insuranceCost;
   int deliveryFees;

    public PaymentModel(int totalCost, int insuranceCost, int deliveryFees) {
        this.totalCost = totalCost;
        this.insuranceCost = insuranceCost;
        this.deliveryFees = deliveryFees;
    }
}
