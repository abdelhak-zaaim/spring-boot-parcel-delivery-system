/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:29
 *  * @modified : 01/05/2024, 19:29
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.repository;

import com.suivi.colis.delix.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRepo extends JpaRepository<SuperAdmin,Long> {
}
