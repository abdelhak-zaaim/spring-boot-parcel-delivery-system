/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 14:52
 *  * @modified : 25/04/2024, 14:52
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.DeliveryArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAreaRepo extends JpaRepository<DeliveryArea, Long> {
}
