/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 19:44
 *  * @modified : 27/04/2024, 19:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.entity;

import com.suivi.colis.suivicolis.model.enums.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@DiscriminatorValue(Role.SUPER_ADMIN_RULE)

public class SuperAdmin extends AdminEmployee {

   private String superAdminNumber;


}