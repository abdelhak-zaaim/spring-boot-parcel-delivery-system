
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

import com.suivi.colis.delix.model.enums.Privilege;
import lombok.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.suivi.colis.delix.entity.PrivilegesGroup}
 */
@Value
public class PrivilegesGroupRequestDto implements Serializable {
    Long id;
    String name;
    List<Privilege> privileges;
    Date creationDate;
    Date lastUpdateDate;
}