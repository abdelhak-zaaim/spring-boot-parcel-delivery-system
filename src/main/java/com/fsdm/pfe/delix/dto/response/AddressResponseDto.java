/*
 *
 *  * @project : DeliX
 *  * @created : 08/05/2024, 20:13
 *  * @modified : 08/05/2024, 20:13
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.Address}
 */
@Value
public class AddressResponseDto implements Serializable {
    Long id;
    String street;
    String city;
    String state;
    String zip;
    String country;

    public AddressResponseDto(Long id, String street, String city, String state, String zip, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }
}