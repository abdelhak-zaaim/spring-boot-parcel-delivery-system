/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 15:25
 *  * @modified : 27/04/2024, 15:25
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long>{
}

