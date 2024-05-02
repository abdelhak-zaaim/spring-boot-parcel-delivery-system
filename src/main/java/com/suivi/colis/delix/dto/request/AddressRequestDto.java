

/*
 * **
 *  * @project : DeliX
 *  * @created : 29/04/2024, 19:57
 *  * @modified : 29/04/2024, 19:57
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.dto.request;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO for {@link com.suivi.colis.delix.entity.Address}
 */
@Value
public class AddressRequestDto implements Serializable {

    String street;
    String city;
    String state;
    String zip;
    String country;
    Date creationDate;
    Date lastUpdateDate;

}