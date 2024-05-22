
/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 01:25
 *  * @modified : 26/04/2024, 01:25
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.converters;

import com.fsdm.pfe.delix.model.ParcelLocation;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.databind.ObjectMapper;

@Converter(autoApply = true)
public class ParcelLocationConverter implements AttributeConverter<ParcelLocation, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ParcelLocation parcelLocation) {
        try {
            // Convert the ParcelLocation object to a JSON string
            return objectMapper.writeValueAsString(parcelLocation);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert ParcelLocation to String", e);
        }
    }

    @Override
    public ParcelLocation convertToEntityAttribute(String parcelLocationString) {
        try {
            // Convert the JSON string back to a ParcelLocation object
            return objectMapper.readValue(parcelLocationString, ParcelLocation.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert String to ParcelLocation", e);
        }
    }
}