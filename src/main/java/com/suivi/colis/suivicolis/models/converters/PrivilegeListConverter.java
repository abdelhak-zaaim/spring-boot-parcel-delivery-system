/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 14:25
 *  * @modified : 25/04/2024, 14:25
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.models.converters;

import com.suivi.colis.suivicolis.models.enums.Privilege;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class PrivilegeListConverter implements AttributeConverter<List<Privilege>, String> {

    @Override
    public String convertToDatabaseColumn(List<Privilege> privileges) {
        return privileges.stream()
                .map(Privilege::name)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<Privilege> convertToEntityAttribute(String privilegesString) {
        return Arrays.stream(privilegesString.split(","))
                .map(Privilege::valueOf)
                .collect(Collectors.toList());
    }
}