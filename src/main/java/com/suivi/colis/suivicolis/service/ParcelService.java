
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 19:09
 *  * @modified : 23/04/2024, 19:09
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.service;

import com.suivi.colis.suivicolis.entity.Parcel;
import com.suivi.colis.suivicolis.model.enums.ParcelStatus;
import com.suivi.colis.suivicolis.repository.ParcelRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service
public class ParcelService {


    private final ParcelRepo parcelRepository;

    public ParcelService(ParcelRepo parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Parcel addParcel(Parcel parcel) {
        parcel.setCodeBar(generateParcelBarcode());
        return parcelRepository.save(parcel);
    }

    public Parcel getParcelById(Long id) {
        return parcelRepository.findById(id).orElse(null);
    }

    public Parcel updateParcel(Parcel parcel) {
        return parcelRepository.save(parcel);
    }

    public void markAsDeleted(Long id) {
        Parcel parcel = parcelRepository.findById(id).orElse(null);
        if (parcel != null) {
            parcel.setStatus(ParcelStatus.DELETED);
            parcelRepository.save(parcel);
        }
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

}




