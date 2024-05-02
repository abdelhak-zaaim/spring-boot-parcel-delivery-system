/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 18:50
 *  * @modified : 01/05/2024, 18:50
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.service;

import com.suivi.colis.delix.entity.Parcel;

public interface ParcelService {
    void deleteParcel(Long id);
    Parcel loadParcelById(Long id);
    Parcel saveParcel(Parcel parcel);
    Parcel updateParcel(Parcel parcel);
    Parcel markAsDeleted(Long id);
}
