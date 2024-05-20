
/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 21:10
 *  * @modified : 20/05/2024, 21:10
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import com.fsdm.pfe.delix.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Value;

import java.io.Serializable;

@Value
public class PaymentResponseDto implements Serializable {

    Long id;
    @Enumerated(EnumType.STRING)
    PaymentMethod paymentMethod;
    float totalCost;
    float insuranceCost;
    float deliveryFees;

}
