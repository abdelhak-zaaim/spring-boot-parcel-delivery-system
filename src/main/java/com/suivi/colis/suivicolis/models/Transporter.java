/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:48
 *  * @modified : 23/04/2024, 18:48
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models;

import com.suivi.colis.suivicolis.models.enums.Role;
import com.suivi.colis.suivicolis.models.enums.VehicleType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue(Role.TRANSPORTER_ROLE)
public class Transporter extends Employee{
    
    private String registrationNumber;

    private String licenseNumber;

    private String vihiculeMtricule;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    
}