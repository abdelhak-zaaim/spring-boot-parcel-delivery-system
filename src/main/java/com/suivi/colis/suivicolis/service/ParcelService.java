/*
 * **
 *  * @project : SuiviColis
 *  * @created : 01/05/2024, 18:50
 *  * @modified : 01/05/2024, 18:50
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service;

import com.suivi.colis.suivicolis.entity.Parcel;

public interface ParcelService {
    void deleteParcel(Long id);
    Parcel loadParcelById(Long id);
    Parcel saveParcel(Parcel parcel);
    Parcel updateParcel(Parcel parcel);
    Parcel markAsDeleted(Long id);
}
