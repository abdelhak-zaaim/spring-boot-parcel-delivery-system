
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 30/04/2024, 19:00
 *  * @modified : 30/04/2024, 19:00
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service.Impl;

import com.suivi.colis.suivicolis.entity.DeliveryAddress;
import com.suivi.colis.suivicolis.repository.DeliveryAddressRepo;
import org.springframework.stereotype.Service;

@Service
public class DeliveryAddressServiceImpl {
   DeliveryAddressRepo deliveryAddressRepo;
   public DeliveryAddressServiceImpl(DeliveryAddressRepo deliveryAddressRepo) {
         this.deliveryAddressRepo = deliveryAddressRepo;
   }

   public void deleteDeliveryAddress(Long id) {
        deliveryAddressRepo.deleteById(id);
   }

   public DeliveryAddress getDeliveryAddress(Long id) {
       return deliveryAddressRepo.findById(id).orElse(null);
   }

    public DeliveryAddress addDeliveryAddress(DeliveryAddress deliveryAddress) {
         return deliveryAddressRepo.save(deliveryAddress);
    }
}
