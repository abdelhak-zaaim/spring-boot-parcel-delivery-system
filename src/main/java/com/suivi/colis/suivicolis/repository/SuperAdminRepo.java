/*
 * **
 *  * @project : SuiviColis
 *  * @created : 01/05/2024, 19:29
 *  * @modified : 01/05/2024, 19:29
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.repository;

import com.suivi.colis.suivicolis.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepo extends JpaRepository<SuperAdmin,Long> {
}
