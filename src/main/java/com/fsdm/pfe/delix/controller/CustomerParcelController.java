/*
 *
 *  * @project : DeliX
 *  * @created : 12/05/2024, 15:44
 *  * @modified : 12/05/2024, 15:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.controller;

import com.fsdm.pfe.delix.entity.location.Province;
import com.fsdm.pfe.delix.service.Impl.ParcelServiceImpl;
import com.fsdm.pfe.delix.service.Impl.location.ProvinceServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CustomerParcelController {
    private final ProvinceServiceImpl provinceService;
    private final ParcelServiceImpl parcelService;

    public CustomerParcelController(ProvinceServiceImpl provinceService, ParcelServiceImpl parcelService) {
        this.provinceService = provinceService;
        this.parcelService = parcelService;
    }

    @GetMapping("/express/add_parcel")
    public String addParcel(Model model) {
        List<Province> provinces = provinceService.loadAll();
        model.addAttribute("provinces", ProvinceServiceImpl.convertListToDto(provinces));

        model.addAttribute("parcelTypes", ParcelServiceImpl.getParcelTypesAsArrayOfMaps());
        return "home/addParcel";
    }


}
