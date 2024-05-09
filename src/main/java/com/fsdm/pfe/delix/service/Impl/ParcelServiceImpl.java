
/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 19:09
 *  * @modified : 23/04/2024, 19:09
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.dto.response.ParcelResponseDto;
import com.fsdm.pfe.delix.entity.Parcel;
import com.fsdm.pfe.delix.model.enums.ParcelStatus;
import com.fsdm.pfe.delix.repository.ParcelRepo;

import com.fsdm.pfe.delix.service.ParcelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepo parcelRepository;

    public ParcelServiceImpl(ParcelRepo parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Override
    public void deleteParcel(Long id) {
        parcelRepository.deleteById(id);
    }

    @Override
    public Parcel loadParcelById(Long id) {
        return parcelRepository.findById(id).orElse(null);
    }

    @Override
    public Parcel saveParcel(Parcel parcel) {
        parcel.setCodeBar(generateParcelBarcode());
        return parcelRepository.save(parcel);
    }

    @Override
    public Parcel updateParcel(Parcel parcel) {
        return parcelRepository.save(parcel);
    }

    public Parcel markAsDeleted(Long id) {
        Parcel parcel = parcelRepository.findById(id).orElse(null);
        if (parcel != null) {
            parcel.setStatus(ParcelStatus.DELETED);
            return parcelRepository.save(parcel);
        }
        return null;
    }

    @Override
    public List<Parcel> loadAllByCreationDateBetween(Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            return parcelRepository.findAllByCreationDateBetween(startDate, endDate);
        } else return List.of();
    }

    public String generateParcelBarcode() {
        SecureRandom random = new SecureRandom();
        String barcode;
        do {
            int randomNumber = 1000000 + random.nextInt(9000000); // generates a random number between 1000000 and 9999999
            barcode = "PR" + randomNumber + "M";
        } while (parcelRepository.existsByCodeBar(barcode));

        return barcode;
    }

    public List<Parcel> getParcelsForCustomer(Long customerId) {
        return parcelRepository.findAllBySenderCustomerId(customerId);
    }


    public ParcelResponseDto convertEntityToResponseDto(Parcel parcel) {
        return new ParcelResponseDto(parcel);
    }

    public List<ParcelResponseDto> convertEntityListToResponseDtoList(List<Parcel> parcels) {
        return parcels.stream().map(this::convertEntityToResponseDto).toList();
    }


}




