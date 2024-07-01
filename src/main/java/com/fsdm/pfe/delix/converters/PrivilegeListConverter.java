
/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 14:25
 *  * @modified : 25/04/2024, 14:25
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.converters;

import com.fsdm.pfe.delix.model.enums.Privilege;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class PrivilegeListConverter implements AttributeConverter<Set<Privilege>, String> {

    @Override
    public String convertToDatabaseColumn(Set<Privilege> privileges) {
        return privileges.stream()
                .map(privilege -> privilege.getType() + "_" + privilege.getAction())
                .collect(Collectors.joining(","));
    }

    @Override
    public Set<Privilege> convertToEntityAttribute(String privilegesString) {
        return Arrays.stream(privilegesString.split(","))
                .map(this::stringToPrivilege)
                .collect(Collectors.toSet());
    }

    private Privilege stringToPrivilege(String privilegeString) {
        String[] parts = privilegeString.split("_");
        Privilege.PrivilegeType type = Privilege.PrivilegeType.valueOf(parts[0]);
        Privilege.PrivilegeAction action = Privilege.PrivilegeAction.valueOf(parts[1]);

        return Arrays.stream(Privilege.values())
                .filter(privilege -> type.equals(privilege.getType()) && action.equals(privilege.getAction()))
                .findFirst()
                .orElse(null);
    }
}