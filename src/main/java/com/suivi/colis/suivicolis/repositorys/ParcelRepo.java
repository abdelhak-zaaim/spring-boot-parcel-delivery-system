/*
 * **
 *  * @project : SuiviColis
 *  * @created : 23/04/2024, 19:10
 *  * @modified : 23/04/2024, 19:10
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.repositorys;

import com.suivi.colis.suivicolis.entities.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcelRepo extends JpaRepository<Parcel, Long> {
    boolean existsByCodeBar(String agencyCode);


}
