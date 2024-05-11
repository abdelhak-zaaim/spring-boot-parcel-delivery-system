/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 13:47
 *  * @modified : 26/04/2024, 01:53
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:50
 *  * @modified : 25/04/2024, 15:45
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : DeliX
 *  * @created : 24/04/2024, 21:29
 *  * @modified : 24/04/2024, 21:29
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.entity;

import com.fsdm.pfe.delix.model.enums.AgencyEmployeeRole;
import com.fsdm.pfe.delix.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@DiscriminatorValue(Role.AGENCY_EMPLOYEE_ROLE)
public class AgencyEmployee extends Employee {

    @ManyToOne(fetch = FetchType.LAZY)
    private Agency associatedAgency;

    private Integer managerLevel;

    @Enumerated(EnumType.STRING)
    private AgencyEmployeeRole agencyEmployeeRole;

}
