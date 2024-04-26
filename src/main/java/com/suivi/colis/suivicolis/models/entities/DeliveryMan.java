/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:50
 *  * @modified : 25/04/2024, 15:41
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 19:17
 *  * @modified : 23/04/2024, 19:17
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models.entities;

import com.suivi.colis.suivicolis.models.MapsLocationPoint;
import com.suivi.colis.suivicolis.models.entities.validations.location.ValidMapsLocationPoint;
import com.suivi.colis.suivicolis.models.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.suivi.colis.suivicolis.models.enums.Role;


@NoArgsConstructor
@AllArgsConstructor
@Data
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
