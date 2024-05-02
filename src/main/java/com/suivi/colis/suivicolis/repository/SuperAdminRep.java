/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 20:27
 *  * @modified : 29/04/2024, 20:27
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.repository;

import com.suivi.colis.suivicolis.entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperAdminRep extends JpaRepository<SuperAdmin, Long> {
}