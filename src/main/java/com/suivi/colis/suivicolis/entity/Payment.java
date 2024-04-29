
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 13:53
 *  * @modified : 26/04/2024, 13:53
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.entity;

import com.suivi.colis.suivicolis.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private float totalCost;
    private float insuranceCost;
    private float deliveryFees;


}