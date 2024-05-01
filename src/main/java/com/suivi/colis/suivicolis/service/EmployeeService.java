/*
 * **
 *  * @project : SuiviColis
 *  * @created : 01/05/2024, 19:15
 *  * @modified : 01/05/2024, 19:15
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service;

import com.suivi.colis.suivicolis.entity.Employee;

public interface EmployeeService {
    void deleteEmployee(Long id);

    Employee loadEmployeeById(Long id);

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);
}
