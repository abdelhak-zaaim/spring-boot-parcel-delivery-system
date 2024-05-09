/*
 *
 *  * @project : DeliX
 *  * @created : 09/05/2024, 21:16
 *  * @modified : 09/05/2024, 21:16
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.controller.admin;

import com.fsdm.pfe.delix.entity.Parcel;
import com.fsdm.pfe.delix.service.Impl.ParcelServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Controller
public class ParcelController {
    private final ParcelServiceImpl parcelService;

    public ParcelController(ParcelServiceImpl parcelService) {
        this.parcelService = parcelService;
    }

    @GetMapping("/admin/parcels")
    public String addParcel(Model model) {
        List<Parcel> parcels = parcelService.loadAllByCreationDateBetween(
                Date.from(Instant.now().minus(1, ChronoUnit.DAYS)),
                Date.from(Instant.now())
        );
        model.addAttribute("parcels", parcelService.convertEntityListToResponseDtoList(parcels));

        return "admin/parcel/parcels";
    }
}
