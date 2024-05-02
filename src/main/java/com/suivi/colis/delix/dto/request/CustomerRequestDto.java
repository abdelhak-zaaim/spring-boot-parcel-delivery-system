
/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 21:41
 *  * @modified : 01/05/2024, 21:41
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.dto.request;

import com.suivi.colis.delix.model.enums.UserStatus;
import com.suivi.colis.delix.util.Constants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Value;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.suivi.colis.delix.entity.Customer}
 */
@Value
public class CustomerRequestDto implements Serializable {
    @NotNull
    @NotEmpty
    String name;

    @NotNull
    @Email
    @NotEmpty
    String email;

    String role;

    @NotEmpty
    @NotNull
    @Pattern(regexp = Constants.MOROCCAN_NUMBER_REGEXP)
    String phoneNumber;

    AddressRequestDto address;
    Date registeredAt;
    Date lastUpdateDate;

    @Enumerated(EnumType.STRING)
    UserStatus status;
    String cin;
    @Past(message = "Date of birth must be in the past")
    Date dateOfBirth;

    double balance;

    @URL(message = "not valid image url")
    String image;
    PrivilegesGroupRequestDto privilegesGroup;
    String customerNumber;
}