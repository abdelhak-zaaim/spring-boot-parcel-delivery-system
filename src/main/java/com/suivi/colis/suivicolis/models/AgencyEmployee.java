/*
 * **
 *  * @project : SuiviColis
 *  * @created : 24/04/2024, 21:29
 *  * @modified : 24/04/2024, 21:29
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models;

import com.suivi.colis.suivicolis.models.enums.Role;
import com.suivi.colis.suivicolis.models.enums.AgencyEmployeeRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue(Role.AGENCY_EMPLOYEE_ROLE)
public class AgencyEmployee extends Employee{
    @ManyToOne
    private Agency associatedAgency;

    private Integer managerLevel;

    @Enumerated(EnumType.STRING)
    private AgencyEmployeeRole agencyEmployeeRole;


}
