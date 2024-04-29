/*
 * **
 *  * @project : SuiviColis
 *  * @created : 29/04/2024, 20:20
 *  * @modified : 29/04/2024, 20:20
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.suivicolis.dto;

import com.suivi.colis.suivicolis.entity.Employee;
import com.suivi.colis.suivicolis.model.enums.UserStatus;
import jakarta.validation.constraints.*;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Employee}
 */
@Value
public class EmployeeDto implements Serializable {
    @NotNull(message = "tye name should be not empty")
    @Size
    @NotEmpty
    String name;
    @NotNull
    @Email
    @NotEmpty
    String email;
    String role;
    String phoneNumber;
    AddressDto address;
    Date registeredAt;
    Date lastUpdateDate;
    UserStatus status;
    String cin;
    @Past
    Date dateOfBirth;
    double balance;
    @URL
    String image;
    String employeeNumber;
}