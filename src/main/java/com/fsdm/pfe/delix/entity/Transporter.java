
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 18:48
 *  * @modified : 23/04/2024, 18:48
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.entity;

import com.fsdm.pfe.delix.model.enums.Role;
import com.fsdm.pfe.delix.model.enums.VehicleType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@DiscriminatorValue(Role.TRANSPORTER_ROLE)

public class Transporter extends VehicleOperatorEmployee {

    private String registrationNumber;


}
