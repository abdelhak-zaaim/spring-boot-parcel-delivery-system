/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:16
 *  * @modified : 01/05/2024, 19:16
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service;

import com.suivi.colis.suivicolis.entity.DeliveryAddress;

public interface DeliveryAddressService {
    void deleteDeliveryAddress(Long id);

    DeliveryAddress loadDeliveryAddress(Long id);

    DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress);

    DeliveryAddress updateDeliveryAddress(DeliveryAddress deliveryAddress);
}
