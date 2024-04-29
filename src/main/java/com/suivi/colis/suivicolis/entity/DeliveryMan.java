
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 19:17
 *  * @modified : 23/04/2024, 19:17
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.entity;

import com.suivi.colis.suivicolis.model.MapsLocationPoint;
import com.suivi.colis.suivicolis.model.enums.Role;
import com.suivi.colis.suivicolis.model.enums.VehicleType;
import com.suivi.colis.suivicolis.validation.location.ValidMapsLocationPoint;
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

    private String vihiculeMtricule;


    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @ValidMapsLocationPoint
    private MapsLocationPoint locationPoint;

    @ManyToOne
    private DeliveryArea deliveryArea;

    @ManyToOne
    private Agency associatedAgency;

}
