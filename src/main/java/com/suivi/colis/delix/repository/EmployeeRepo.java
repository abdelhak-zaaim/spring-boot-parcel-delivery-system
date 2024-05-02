/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 19:13
 *  * @modified : 23/04/2024, 19:13
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.repository;

import com.suivi.colis.delix.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {



}
