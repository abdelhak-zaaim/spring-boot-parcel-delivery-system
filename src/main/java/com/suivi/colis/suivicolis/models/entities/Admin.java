/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:50
 *  * @modified : 25/04/2024, 14:57
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.suivicolis.models.entities;

import com.suivi.colis.suivicolis.models.enums.Role;
import com.suivi.colis.suivicolis.models.entities.validations.admin.AdminValidate;
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
@AdminValidate
public class Admin extends Employee {

   @ManyToOne
   private PrivilegesGroup privilegesGroup;

   private Integer adminLevel;


}
