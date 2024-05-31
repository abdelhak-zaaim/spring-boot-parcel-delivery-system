
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 18:37
 *  * @modified : 23/04/2024, 18:37
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.entity;

import com.fsdm.pfe.delix.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

/**
 * This class represents an Employee in the system.
 * It extends the User class, inheriting its properties and behaviors.
 * An Employee is distinguished by a unique employee number and has a salary.
 * Each Employee is assigned by an Admin.
 *
 * @Entity annotation indicates that this class is a JPA entity.
 * @DiscriminatorValue annotation is used to define the value for the discriminator column for instances of the Employee class.
 *
 * @AllArgsConstructor generates a constructor with 1 parameter for each field in your class. Fields are initialized in the order they are declared.
 * @Getter generates getters for all fields.
 * @Setter generates setters for all fields.
 * @ToString generates a toString method.
 * @RequiredArgsConstructor generates a constructor for all final fields, with parameter order same as field order.
 *
 * @see com.fsdm.pfe.delix.entity.User
 * @see com.fsdm.pfe.delix.entity.Admin
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@DiscriminatorValue(Role.EMPLOYEE_ROLE)
public class Employee extends User {

    /**
     * Unique identifier for the employee.
     * @Column annotation specifies that this field should be mapped to a column in the database. The 'unique' attribute ensures that the column will have unique values.
     */
    @Column(unique = true)
    private String employeeNumber;

    /**
     * The salary of the employee.
     */
    private double salary;

    /**
     * The admin who assigned this employee.
     * @ManyToOne annotation indicates that this field should be mapped to a foreign key column in the database.
     * @JoinColumn annotation indicates the column used for joining an entity association or element collection.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_by")
    private Admin assignedBy;
}
