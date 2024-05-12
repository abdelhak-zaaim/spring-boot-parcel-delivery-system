/*
 *
 *  * @project : DeliX
 *  * @created : 12/05/2024, 20:08
 *  * @modified : 12/05/2024, 20:08
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.request;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.fsdm.pfe.delix.entity.DeliveryAddress}
 */
@Value
public class DeliveryAddressDto implements Serializable {
    Long id;
    String area;
    String address;
    String contactFirstName;
    String contactLastName;
    String contactNumber;
    String contactEmail;
}