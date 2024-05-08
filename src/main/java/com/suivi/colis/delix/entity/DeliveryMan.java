
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 19:17
 *  * @modified : 23/04/2024, 19:17
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.entity;

import com.suivi.colis.delix.model.MapsLocationPoint;
import com.suivi.colis.delix.model.enums.Role;
import com.suivi.colis.delix.model.enums.VehicleType;
import com.suivi.colis.delix.validation.location.ValidMapsLocationPoint;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@DiscriminatorValue(Role.DELIVERY_MAN_ROLE)
public class DeliveryMan extends Employee {

    private String licenseNumber;

    private String vehicularMatriculate;


    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ValidMapsLocationPoint
    private MapsLocationPoint locationPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryArea deliveryArea;

    @ManyToOne(fetch = FetchType.LAZY)
    private Agency associatedAgency;

}
