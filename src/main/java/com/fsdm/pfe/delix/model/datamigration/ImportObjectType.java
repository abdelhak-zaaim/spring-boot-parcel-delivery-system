/*
 *
 *  * @project : DeliX
 *  * @created : 30/05/2024, 18:09
 *  * @modified : 30/05/2024, 18:09
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.model.datamigration;

import lombok.Value;


public enum ImportObjectType {
    LOCATION,
    PARCEL,
    DELIVERY_MAN,
    AGENCY,
    USER,
    TRANSPORTER,
    PROVINCE,
    CITY
}
