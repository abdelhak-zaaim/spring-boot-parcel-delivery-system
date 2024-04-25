/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 18:51
 *  * @modified : 25/04/2024, 18:39
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 18:39
 *  * @modified : 25/04/2024, 14:57
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

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

import java.util.Random;

@Service
public class ParcelService {

    @Autowired
    private ParcelRepo parcelRepository;

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

    public void deleteParcelById(Long id) {
        parcelRepository.deleteById(id);
    }


    public String generateParcelBarcode() {
        Random random = new Random();
        String barcode;
        do {
            int randomNumber = 1000000 + random.nextInt(9000000); // generates a random number between 1000000 and 9999999
            barcode = "PR" + randomNumber + "M";
        } while (parcelRepository.existsByCodeBar(barcode));

        return barcode;
    }
}




