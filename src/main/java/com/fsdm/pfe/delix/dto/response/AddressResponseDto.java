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

import com.fsdm.pfe.delix.entity.Address;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.Address}
 */
@Value
public class AddressResponseDto implements Serializable {
    Long id;
    String address;
    AreaResponseDto area;

    public AddressResponseDto(Address address) {
        this.id = address.getId();
        this.address = address.getAddress();
        this.area = new AreaResponseDto(address.getArea());

    }
}