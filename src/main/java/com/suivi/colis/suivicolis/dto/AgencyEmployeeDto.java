/*
 * **
 *  * @project : SuiviColis
 *  * @created : 29/04/2024, 19:58
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 29/04/2024, 19:57
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.dto;

import com.suivi.colis.suivicolis.entity.AgencyEmployee;
import com.suivi.colis.suivicolis.model.enums.AgencyEmployeeRole;
import com.suivi.colis.suivicolis.model.enums.UserStatus;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link AgencyEmployee}
 */
@Value
public class AgencyEmployeeDto implements Serializable {
    String name;
    String email;
    String phoneNumber;
    Date registeredAt;
    Date lastUpdateDate;
    UserStatus status;
    String cin;
    Date dateOfBirth;
    double balance;
    String image;
    String employeeNumber;
    double salary;
    Integer managerLevel;
    AgencyEmployeeRole agencyEmployeeRole;
}