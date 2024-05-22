
/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:42
 *  * @modified : 26/04/2024, 01:42
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.converters;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fsdm.pfe.delix.model.MapsLocationPoint;

import java.io.IOException;
import java.util.List;

@Converter(autoApply = true)
public class ListLocationPointListConverter implements AttributeConverter<List<MapsLocationPoint>, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<MapsLocationPoint> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert list of location points to JSON", e);
        }
    }

    @Override
    public List<MapsLocationPoint> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<MapsLocationPoint>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert JSON to list of location points", e);
        }
    }
}
