/*
 * **
 *  * @project : DeliX
 *  * @created : 23/04/2024, 19:10
 *  * @modified : 23/04/2024, 19:10
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.Parcel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ParcelRepo extends JpaRepository<Parcel, Long> {
    boolean existsByCodeBar(String agencyCode);

    List<Parcel> findAllBySenderCustomerId(Long customerId);

    Parcel findByCodeBar(String codeBar);

    List<Parcel> findAllByCreationDateBetween(Date startDate, Date endDate);
}
