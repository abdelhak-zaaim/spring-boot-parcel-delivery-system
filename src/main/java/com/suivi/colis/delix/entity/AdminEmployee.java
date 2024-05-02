

/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:50
 *  * @modified : 25/04/2024, 14:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.delix.entity;

import com.suivi.colis.delix.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@DiscriminatorValue(Role.ADMIN_EMPLOYEE_ROLE)
public class AdminEmployee extends Employee {

private String adminEmployeeNumber;


}
