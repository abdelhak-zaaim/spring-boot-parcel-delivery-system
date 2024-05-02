
/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 20:28
 *  * @modified : 25/04/2024, 20:28
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service.Impl;

import com.suivi.colis.suivicolis.entity.Agency;
import com.suivi.colis.suivicolis.repository.AgencyRepo;
import com.suivi.colis.suivicolis.service.AgencyService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class AgencyServiceImpl implements AgencyService {


    private final AgencyRepo agencyRepository;

    public AgencyServiceImpl(AgencyRepo agencyRepository) {
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
        return agencyRepository.save(agency);
    }

    @Override
    public Agency updateAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    public String generateAgencyCode() {
        SecureRandom random = new SecureRandom();
        String generatedCode;
        do {
            int randomNumber = 10000000 + random.nextInt(90000000);
            generatedCode = "AGC" + randomNumber;
        } while (agencyRepository.existsByAgencyCode(generatedCode));
        return generatedCode;
    }
}
