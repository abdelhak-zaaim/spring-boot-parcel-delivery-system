

/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 19:58
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 19:57
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.dto.request;

import com.suivi.colis.delix.entity.AgencyEmployee;
import com.suivi.colis.delix.model.enums.AgencyEmployeeRole;
import com.suivi.colis.delix.model.enums.UserStatus;
import com.suivi.colis.delix.util.Constants;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link AgencyEmployee}
 */
@Value
public class AgencyEmployeeDto implements Serializable {
    Long id;
    @NotNull
    @NotBlank
    String name;

    @NotNull
    @Email
    String email;

    @NotNull
    @Pattern(regexp = Constants.MOROCCAN_NUMBER_REGEXP)
    String phoneNumber;


    @Enumerated(EnumType.STRING)
    UserStatus status;
    String cin;
    Date dateOfBirth;
    String image;
    String employeeNumber;
    double salary;
    Integer managerLevel;
    AgencyEmployeeRole agencyEmployeeRole;
}