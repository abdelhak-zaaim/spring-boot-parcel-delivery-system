/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 19:13
 *  * @modified : 23/04/2024, 19:13
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.repositorys;

import com.suivi.colis.suivicolis.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {



}
