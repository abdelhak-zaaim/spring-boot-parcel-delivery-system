
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 20:41
 *  * @modified : 23/04/2024, 20:41
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.entities;


import com.suivi.colis.suivicolis.entities.converters.PrivilegeListConverter;
import com.suivi.colis.suivicolis.models.enums.Privilege;
import com.suivi.colis.suivicolis.validations.admin.privilegesgroupvalidate.PrivilegeValidation;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
