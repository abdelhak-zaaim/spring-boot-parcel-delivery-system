
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 19:12
 *  * @modified : 23/04/2024, 19:12
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.service.Impl;

import com.suivi.colis.delix.entity.Employee;
import com.suivi.colis.delix.repository.EmployeeRepo;
import com.suivi.colis.delix.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepository;

    public EmployeeServiceImpl(EmployeeRepo employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee loadEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


}
