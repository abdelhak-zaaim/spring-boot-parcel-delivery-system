
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 18:37
 *  * @modified : 23/04/2024, 18:37
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.entities;

import com.suivi.colis.suivicolis.models.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue(Role.EMPLOYEE_ROLE)
public class Employee extends User {

    @Column(unique = true)
    private String employeeNumber;
    private double salary;
    @ManyToOne
    @JoinColumn(name = "assigned_by")
    private Admin assignedBy;

}
