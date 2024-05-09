/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 18:17
 *  * @modified : 04/05/2024, 18:17
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.dto.response;

import com.fsdm.pfe.delix.entity.Address;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.Address}
 */
@Value
public class AddressDto implements Serializable {
    String street;
    String city;
    String state;
    String zip;

    public AddressDto(Address address) {
    this.street = address.getStreet();
    this.city = address.getCity();
    this.state = address.getState();
    this.zip = address.getZip();
}
}