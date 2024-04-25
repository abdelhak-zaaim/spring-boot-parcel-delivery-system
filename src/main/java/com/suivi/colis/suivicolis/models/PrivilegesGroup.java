/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 20:41
 *  * @modified : 23/04/2024, 20:41
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models;


import com.suivi.colis.suivicolis.models.converters.PrivilegeListConverter;
import com.suivi.colis.suivicolis.models.enums.Privilege;
import com.suivi.colis.suivicolis.validations.privilegesgroupvalidate.PrivilegeValidation;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@PrivilegeValidation
public class PrivilegesGroup {
public PrivilegesGroup(String name, List<Privilege> privileges) {
        this.name = name;
        this.privileges = privileges;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Convert(converter = PrivilegeListConverter.class)
    private List<Privilege> privileges;


}
