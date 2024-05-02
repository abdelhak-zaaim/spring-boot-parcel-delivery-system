/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 20:28
 *  * @modified : 29/04/2024, 20:28
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.repository;

import com.suivi.colis.delix.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginLogRepo extends JpaRepository<LoginLog, Long> {
}