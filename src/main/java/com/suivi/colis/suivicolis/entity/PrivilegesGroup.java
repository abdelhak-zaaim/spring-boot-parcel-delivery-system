
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 20:41
 *  * @modified : 23/04/2024, 20:41
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.suivi.colis.suivicolis.entity.converters.PrivilegeListConverter;
import com.suivi.colis.suivicolis.model.enums.Privilege;
import com.suivi.colis.suivicolis.validation.admin.privilegesgroupvalidate.PrivilegeValidation;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


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

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;

    @Convert(converter = PrivilegeListConverter.class)
    private List<Privilege> privileges;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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
