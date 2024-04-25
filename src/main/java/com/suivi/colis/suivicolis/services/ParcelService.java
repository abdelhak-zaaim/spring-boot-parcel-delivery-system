/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 19:09
 *  * @modified : 23/04/2024, 19:09
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.services;

import com.suivi.colis.suivicolis.models.Parcel;
import com.suivi.colis.suivicolis.repositorys.ParcelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {

    @Autowired
    private ParcelRepo parcelRepository;

    public Parcel addParcel(Parcel parcel) {
        return parcelRepository.save(parcel);
    }

    public Parcel getParcelById(Long id) {
        return parcelRepository.findById(id).orElse(null);
    }

    public Parcel updateParcel(Parcel parcel) {
        return parcelRepository.save(parcel);
    }

    public void deleteParcelById(Long id) {
        parcelRepository.deleteById(id);
    }
}