/*
 * **
 *  * @project : SuiviColis
 *  * @created : 27/04/2024, 15:25
 *  * @modified : 27/04/2024, 15:25
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.repositorys;

import com.suivi.colis.suivicolis.entities.Address;
import com.suivi.colis.suivicolis.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{
}

