/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:15
 *  * @modified : 01/05/2024, 19:15
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.Employee;

public interface EmployeeService {
    void deleteEmployee(Long id);

    Employee loadEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Employee updateEmployeeEmail(Long employeeId, String newEmail);
}
