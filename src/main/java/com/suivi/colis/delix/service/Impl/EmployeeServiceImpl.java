
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
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepository;
    private final UserServiceImpl userService;

    public EmployeeServiceImpl(EmployeeRepo employeeRepository, UserServiceImpl userService) {
        this.employeeRepository = employeeRepository;
        this.userService = userService;
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
        employee.setPassword(userService.encodePassword(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeEmail(@NotNull Long employeeId, @Email String newEmail) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee with id " + employeeId + " not found"));

        employee.setEmail(newEmail);
        return employeeRepository.save(employee);

    }


}
