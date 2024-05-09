
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

import com.suivi.colis.delix.dto.request.AgencyRequestDto;
import com.suivi.colis.delix.dto.response.AgencyResponseDto;
import com.suivi.colis.delix.entity.Address;
import com.suivi.colis.delix.entity.Agency;
import com.suivi.colis.delix.model.MapsLocationPoint;
import com.suivi.colis.delix.repository.AgencyRepo;
import com.suivi.colis.delix.service.AddressService;
import com.suivi.colis.delix.service.AgencyService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class AgencyServiceImpl implements AgencyService {
    private final AddressServiceImpl addressService;

    private final AgencyRepo agencyRepository;

    public AgencyServiceImpl(AddressServiceImpl addressService, AgencyRepo agencyRepository) {
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

    public Agency convertRequestDtoToEntity(AgencyRequestDto agencyRequestDto) {
        Agency agency = new Agency();
        agency.setAgencyName(agencyRequestDto.getAgencyName());
        agency.setAgencyAddress(addressService.convertDtoToEntity(agencyRequestDto.getAgencyAddress()));
        agency.setAgencyContactNumber(agencyRequestDto.getAgencyContactNumber());
        agency.setAgencyEmail(agencyRequestDto.getAgencyEmail());
        agency.setLocationPoint(new MapsLocationPoint(agencyRequestDto.getLocationPoint().getLatitude(),
                agencyRequestDto.getLocationPoint().getLongitude()));

        return agency;
    }

    public AgencyResponseDto convertEntityToResponseDto(Agency agency) {
        return new AgencyResponseDto(agency);
    }

    public List<AgencyResponseDto> convertEntityListToResponseDtoList(List<Agency> agencies) {
        return agencies.stream().map(this::convertEntityToResponseDto).toList();
    }



}
