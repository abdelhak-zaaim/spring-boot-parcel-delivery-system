/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 21:47
 *  * @modified : 20/05/2024, 21:47
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.repository;

import com.fsdm.pfe.delix.entity.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PricingRepo extends JpaRepository<Pricing, Long> {
    Pricing findPricingById(Long id);

    Optional<Pricing> findTopByOrderByIdDesc();

}