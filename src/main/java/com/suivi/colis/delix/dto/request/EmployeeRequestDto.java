
/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 20:20
 *  * @modified : 29/04/2024, 20:20
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.delix.dto.request;

import com.suivi.colis.delix.entity.Employee;
import com.suivi.colis.delix.model.enums.UserStatus;
import com.suivi.colis.delix.util.Constants;
import jakarta.validation.constraints.*;
import lombok.Value;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link Employee}
 */

@Value
public class EmployeeRequestDto implements Serializable {
    Long id;
    @NotNull(message = "tye name should be not empty")
    @Size
    @NotEmpty
    String name;
    @NotNull
    @Email
    @NotEmpty
    String email;
    String role;

    @Pattern(regexp = Constants.MOROCCAN_NUMBER_REGEXP)
    String phoneNumber;
    AddressRequestDto address;
    UserStatus status;
    String cin;
    @Past
    Date dateOfBirth;
    double balance;
    @URL
    String image;
    String employeeNumber;
}