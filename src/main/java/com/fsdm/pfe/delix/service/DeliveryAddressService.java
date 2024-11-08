/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:16
 *  * @modified : 01/05/2024, 19:16
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.DeliveryAddress;

public interface DeliveryAddressService {
    void deleteDeliveryAddress(Long id);

    DeliveryAddress loadDeliveryAddress(Long id);

    DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress);

    DeliveryAddress updateDeliveryAddress(DeliveryAddress deliveryAddress);
}
