
/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 20:28
 *  * @modified : 25/04/2024, 20:28
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.service.Impl;

import com.suivi.colis.delix.entity.Agency;
import com.suivi.colis.delix.repository.AgencyRepo;
import com.suivi.colis.delix.service.AddressService;
import com.suivi.colis.delix.service.AgencyService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {
    private final AddressService addressService;

    private final AgencyRepo agencyRepository;

    public AgencyServiceImpl(AddressService addressService, AgencyRepo agencyRepository) {
        this.addressService = addressService;
        this.agencyRepository = agencyRepository;
    }

    @Override
    public void deleteAgency(Long id) {
        agencyRepository.deleteById(id);
    }

    @Override
    public Agency loadAgencyById(Long id) {
        return agencyRepository.findById(id).orElse(null);
    }

    @Override
    public Agency saveAgency(Agency agency) {
     //   agency.setAgencyAddress(addressService.saveAddress(agency.getAgencyAddress()));
        return agencyRepository.save(agency);
    }

    @Override
    public Agency updateAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    @Override
    public List<Agency> getAllAgencies() {
        return agencyRepository.findAll();
    }


}
