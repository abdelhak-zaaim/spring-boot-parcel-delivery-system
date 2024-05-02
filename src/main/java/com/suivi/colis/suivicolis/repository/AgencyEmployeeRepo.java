/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 14:50
 *  * @modified : 25/04/2024, 14:50
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.repository;

import com.suivi.colis.suivicolis.entity.AgencyEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyEmployeeRepo extends JpaRepository<AgencyEmployee, Long> {
}
