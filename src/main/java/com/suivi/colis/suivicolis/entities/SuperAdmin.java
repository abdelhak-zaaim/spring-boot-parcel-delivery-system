/*
 * **
 *  * @project : SuiviColis
 *  * @created : 27/04/2024, 19:44
 *  * @modified : 27/04/2024, 19:44
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.entities;

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
@DiscriminatorValue(Role.SUPER_ADMIN_RULE)

public class SuperAdmin extends AdminEmployee {

   private String superAdminNumber;
}