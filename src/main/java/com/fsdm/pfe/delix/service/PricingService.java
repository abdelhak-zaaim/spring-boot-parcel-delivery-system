/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 21:47
 *  * @modified : 20/05/2024, 21:47
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service;

import com.fsdm.pfe.delix.entity.Pricing;

import java.util.Optional;

public interface PricingService {
    Optional<Pricing> loadPricingById(Long id);

    Optional<Pricing> loadPricingByBasePrice(double basePrice);

    void deletePricing(Long id);

    Pricing updatePricing(Pricing pricing);

    Pricing savePricing(Pricing pricing);

    Optional<Pricing> loadCurrentPricing();
}
