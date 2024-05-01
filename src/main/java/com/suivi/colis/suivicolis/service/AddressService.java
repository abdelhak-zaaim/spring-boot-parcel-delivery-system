/*
 * **
 *  * @project : SuiviColis
 *  * @created : 27/04/2024, 15:27
 *  * @modified : 27/04/2024, 15:27
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service;

import com.suivi.colis.suivicolis.entity.Address;
import org.springframework.stereotype.Service;
import com.suivi.colis.suivicolis.repository.AddressRepo;
@Service
public class AddressService {
   private final AddressRepo addressRepo;
   public AddressService(AddressRepo addressRepo) {
       this.addressRepo = addressRepo;
   }

   public Address addAddress(Address address) {
       return addressRepo.save(address);
   }
   public Address updateAddress(Address address) {
       return addressRepo.save(address);
   }
    public void deleteAddress(Long id) {
         addressRepo.deleteById(id);
    }
    public Address getAddress(Long id) {
        return addressRepo.findById(id).orElse(null);
    }

}
