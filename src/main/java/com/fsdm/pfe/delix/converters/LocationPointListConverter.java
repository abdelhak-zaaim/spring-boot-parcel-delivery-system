
/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 18:42
 *  * @modified : 26/04/2024, 18:42
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsdm.pfe.delix.model.MapsLocationPoint;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
// curently we are not using this class, becoouse we changed how LocationPointListConverter stored , but we can use it in the future

// FIXME: we dont need this converter, we can remove it
@Converter(autoApply = true)
public class LocationPointListConverter implements AttributeConverter<MapsLocationPoint, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(MapsLocationPoint attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert list of location points to JSON", e);
        }
    }

    @Override
    public MapsLocationPoint convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<MapsLocationPoint>() {
            });
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON to list of location points", e);
        }
    }
}
