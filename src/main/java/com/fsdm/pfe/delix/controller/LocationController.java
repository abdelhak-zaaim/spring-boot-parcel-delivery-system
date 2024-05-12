/*
 *
 *  * @project : DeliX
 *  * @created : 12/05/2024, 21:31
 *  * @modified : 12/05/2024, 21:31
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.controller;

import com.fsdm.pfe.delix.dto.response.AreaResponseDto;
import com.fsdm.pfe.delix.dto.response.CityResponseDto;
import com.fsdm.pfe.delix.dto.response.ResponseDataDto;
import com.fsdm.pfe.delix.service.Impl.location.AreaServiceImpl;
import com.fsdm.pfe.delix.service.Impl.location.CityServiceImpl;
import com.fsdm.pfe.delix.service.Impl.location.ProvinceServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LocationController {
    private final ProvinceServiceImpl provinceService;
    private final CityServiceImpl cityService;
    private final AreaServiceImpl areaService;

    public LocationController(ProvinceServiceImpl provinceService, CityServiceImpl cityService, AreaServiceImpl areaService) {
        this.provinceService = provinceService;
        this.cityService = cityService;
        this.areaService = areaService;
    }

    @GetMapping("/express/location/city")

    public ResponseEntity<ResponseDataDto> locationCity(@RequestParam String code) {

        if (code == null || code.isEmpty()) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error("Code is required").build());
        }

        try {
            List<CityResponseDto> cities = CityServiceImpl.convertListToDto(cityService.loadByProvinceCode(code));
            return ResponseEntity.ok(ResponseDataDto.builder().data(cities).success(true).error(null).build());
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(e.getMessage()).build());
        }

    }
    @GetMapping("/express/location/area")

    public ResponseEntity<ResponseDataDto> locationArea(@RequestParam String code) {

        if (code == null || code.isEmpty()) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error("Code is required").build());
        }

        try {
            List<AreaResponseDto> cities = AreaServiceImpl.convertListToDto(areaService.loadByCityCode(code));
            return ResponseEntity.ok(ResponseDataDto.builder().data(cities).success(true).error(null).build());
        } catch (Exception e) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(e.getMessage()).build());
        }

    }

}
