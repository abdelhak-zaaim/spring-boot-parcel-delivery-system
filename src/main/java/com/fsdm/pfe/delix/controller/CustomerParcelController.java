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

import com.fsdm.pfe.delix.dto.request.GetQuoteRequestDto;
import com.fsdm.pfe.delix.dto.request.ParcelRequestDto;
import com.fsdm.pfe.delix.dto.response.ParcelResponseDto;
import com.fsdm.pfe.delix.dto.response.ResponseDataDto;
import com.fsdm.pfe.delix.entity.Parcel;
import com.fsdm.pfe.delix.entity.location.Province;
import com.fsdm.pfe.delix.service.Impl.CustomerServiceImpl;
import com.fsdm.pfe.delix.service.Impl.ParcelServiceImpl;
import com.fsdm.pfe.delix.service.Impl.location.ProvinceServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class CustomerParcelController {
    private final Validator validator;
    private final AuthenticationManager authenticationManager;

    private final ProvinceServiceImpl provinceService;
    private final ParcelServiceImpl parcelService;
    private final CustomerServiceImpl customerService;

    public CustomerParcelController(Validator validator, @Qualifier("authenticationManagerUser") AuthenticationManager authenticationManager, ProvinceServiceImpl provinceService, ParcelServiceImpl parcelService, CustomerServiceImpl customerService) {
        this.validator = validator;
        this.authenticationManager = authenticationManager;
        this.provinceService = provinceService;
        this.parcelService = parcelService;
        this.customerService = customerService;
    }

    @GetMapping("/express/add-parcel")
    public String addParcel(Model model) {
        List<Province> provinces = provinceService.loadAll();
        model.addAttribute("provinces", ProvinceServiceImpl.convertListToDto(provinces));
        model.addAttribute("parcelTypes", ParcelServiceImpl.getParcelTypesAsArrayOfMaps());
        return "home/addParcel";
    }


    @PostMapping("/express/add-parcel")
    public ResponseEntity<ResponseDataDto> addParcelRequest(ParcelRequestDto parcelRequestDto) {
        DataBinder binder = new DataBinder(parcelRequestDto);
        binder.setValidator(validator);
        binder.validate();
        BindingResult result = binder.getBindingResult();

        if (result.hasErrors()) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(result.getAllErrors()).message("please verify the inputs").build());
        }


        try {
        parcelService.saveParcelFromDto(parcelRequestDto);
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(parcelRequestDto).success(false).error(e.getMessage()).message(e.getMessage()).build());
        }

        return ResponseEntity.ok(ResponseDataDto.builder().data(parcelRequestDto).success(true).message("the parcel added successfully").error(null).build());

    }


    @GetMapping("/order-quote")
    public String getQuote(Model model) {
        List<Province> provinces = provinceService.loadAll();
        model.addAttribute("provinces", ProvinceServiceImpl.convertListToDto(provinces));
        model.addAttribute("parcelTypes", ParcelServiceImpl.getParcelTypesAsArrayOfMaps());
        return "home/orderQuote";
    }

    @PostMapping("/order-quote")
    public ResponseEntity<ResponseDataDto> getQuoteRequest(GetQuoteRequestDto getQuoteRequestDto) {
        DataBinder binder = new DataBinder(getQuoteRequestDto);
        binder.setValidator(validator);
        binder.validate();
        BindingResult result = binder.getBindingResult();

        if (result.hasErrors()) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(result.getAllErrors()).message("please verify the inputs").build());
        }
        int totalCost = parcelService.generateQuoteFromRequestDto(getQuoteRequestDto).getTotalCost();

        return ResponseEntity.ok(ResponseDataDto.builder().data(totalCost).success(true).error(null).message("votre devis est : " + totalCost).build());

    }


    @GetMapping("/express/my-orders")
    public String getMyOrders(Model model, HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        List<Parcel> parcels = customerService.getParcelsForCustomerByUserName(principal.getName());
        //convert parcels to list ParcelResponseDto



        model.addAttribute("parcels", parcelService.convertEntityListToResponseDtoList(parcels));
        return "home/myOrders";
    }
}
