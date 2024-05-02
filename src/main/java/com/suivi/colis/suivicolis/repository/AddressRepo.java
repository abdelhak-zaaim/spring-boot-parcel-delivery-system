/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 15:25
 *  * @modified : 27/04/2024, 15:25
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.repository;

import com.suivi.colis.suivicolis.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AddressRepo extends JpaRepository<Address, Long>{
}

