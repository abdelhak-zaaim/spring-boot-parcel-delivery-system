
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 18:37
 *  * @modified : 23/04/2024, 18:37
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.entity;

import com.suivi.colis.delix.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
