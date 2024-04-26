/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 01:52
 *  * @modified : 26/04/2024, 00:54
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 20:41
 *  * @modified : 23/04/2024, 20:41
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models.entities;


import com.suivi.colis.suivicolis.models.converters.PrivilegeListConverter;
import com.suivi.colis.suivicolis.models.enums.Privilege;
import com.suivi.colis.suivicolis.models.entities.validations.privilegesgroupvalidate.PrivilegeValidation;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@PrivilegeValidation
public class PrivilegesGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Convert(converter = PrivilegeListConverter.class)
    private List<Privilege> privileges;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @PrePersist
    protected void onCreated() {
        Date date = new Date();
        this.creationDate = date;
        this.lastUpdateDate = date;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = new Date();
    }

    public PrivilegesGroup(String name, List<Privilege> privileges) {
        this.name = name;
        this.privileges = privileges;
    }
}
