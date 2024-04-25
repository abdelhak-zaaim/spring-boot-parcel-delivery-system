/*
 * **
 *  * @project : SuiviColis
 *  * @created : 24/04/2024, 21:20
 *  * @modified : 24/04/2024, 21:20
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models;

import com.suivi.colis.suivicolis.models.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue(Role.ADMIN_ROLE)
public class Admin extends Employee{

   @ManyToOne
   private PrivilegesGroup privilegesGroup;

   private Integer adminLevel;


}