
/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 15:27
 *  * @modified : 27/04/2024, 15:27
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service.Impl;

import com.suivi.colis.suivicolis.entity.Address;
import com.suivi.colis.suivicolis.service.AddressService;
import org.springframework.stereotype.Service;
import com.suivi.colis.suivicolis.repository.AddressRepo;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;

    public AddressServiceImpl(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    public Address updateAddress(Address address) {
        return addressRepo.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepo.deleteById(id);
    }

    @Override
    public Address loadAddressById(Long id) {
        return addressRepo.findById(id).orElse(null);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepo.save(address);
    }


}
