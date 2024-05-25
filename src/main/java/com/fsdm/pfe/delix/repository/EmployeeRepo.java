/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 19:13
 *  * @modified : 23/04/2024, 19:13
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

}
