
/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 15:27
 *  * @modified : 27/04/2024, 15:27
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.dto.request.AddressRequestDto;
import com.fsdm.pfe.delix.entity.Address;
import com.fsdm.pfe.delix.entity.location.Area;
import com.fsdm.pfe.delix.service.AddressService;
import com.fsdm.pfe.delix.service.Impl.location.AreaServiceImpl;
import org.springframework.stereotype.Service;
import com.fsdm.pfe.delix.repository.AddressRepo;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepo addressRepo;

    private final AreaServiceImpl areaService;
    public AddressServiceImpl(AddressRepo addressRepo, AreaServiceImpl areaService) {
        this.addressRepo = addressRepo;
        this.areaService = areaService;
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
