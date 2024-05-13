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

import com.fsdm.pfe.delix.dto.request.ParcelRequestDto;
import com.fsdm.pfe.delix.dto.response.ErrorResponseDto;
import com.fsdm.pfe.delix.dto.response.ResponseDataDto;
import com.fsdm.pfe.delix.entity.location.Province;
import com.fsdm.pfe.delix.service.Impl.ParcelServiceImpl;
import com.fsdm.pfe.delix.service.Impl.location.ProvinceServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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


    @PostMapping("/express/add_parcel")
    public ResponseEntity<ResponseDataDto> addParcelRequest(@Valid ParcelRequestDto parcelRequestDto, BindingResult bindingResult,Model model) {

if(bindingResult.hasErrors()){

    ResponseEntity.ok(ResponseDataDto.builder().data(bindingResult.getAllErrors()).success(true).error(null).build());



   // return ResponseEntity.ok(ErrorResponseDto.builder().data(null).success(false).errors(bindingResult.getAllErrors()).build());
}


        System.out.println(parcelRequestDto);

        return ResponseEntity.ok(ResponseDataDto.builder().data(parcelRequestDto).success(true).error(null).build());
    }

}
