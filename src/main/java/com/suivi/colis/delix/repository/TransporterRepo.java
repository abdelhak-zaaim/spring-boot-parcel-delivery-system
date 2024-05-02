/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 14:48
 *  * @modified : 25/04/2024, 14:48
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.repository;

import com.suivi.colis.delix.entity.Transporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporterRepo extends JpaRepository<Transporter, Long> {
}
