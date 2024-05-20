/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 21:50
 *  * @modified : 20/05/2024, 21:50
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.entity.Pricing;
import com.fsdm.pfe.delix.repository.PricingRepo;
import com.fsdm.pfe.delix.service.PricingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PricingServiceImpl implements PricingService {
    private final PricingRepo pricingRepo;

    public PricingServiceImpl(PricingRepo pricingRepo) {
        this.pricingRepo = pricingRepo;
    }

    @Override
    public Optional<Pricing> loadPricingById(Long id) {
        return pricingRepo.findById(id);
    }

    @Override
    public Optional<Pricing> loadPricingByBasePrice(double basePrice) {
        return pricingRepo.findById((long) basePrice);
    }

    @Override
    public void deletePricing(Long id) {
        pricingRepo.deleteById(id);

    }

    @Override
    public Pricing updatePricing(Pricing pricing) {
        return pricingRepo.save(pricing);
    }

    @Override
    public Pricing savePricing(Pricing pricing) {
        return pricingRepo.save(pricing);
    }

    @Override
    public Optional<Pricing> loadCurrentPricing() {
        return pricingRepo.findTopByOrderByIdDesc();
    }


}
