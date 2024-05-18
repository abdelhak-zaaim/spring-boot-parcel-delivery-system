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
import com.fsdm.pfe.delix.entity.location.Area;
import com.fsdm.pfe.delix.entity.location.City;
import com.fsdm.pfe.delix.entity.location.Province;
import com.fsdm.pfe.delix.service.Impl.location.AreaServiceImpl;
import com.fsdm.pfe.delix.service.Impl.location.CityServiceImpl;
import com.fsdm.pfe.delix.service.Impl.location.ProvinceServiceImpl;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class LocationController {
    private final ProvinceServiceImpl provinceService;
    private final CityServiceImpl cityService;
    private final AreaServiceImpl areaService;
    private final ProvinceServiceImpl provinceServiceImpl;

    public LocationController(ProvinceServiceImpl provinceService, CityServiceImpl cityService, AreaServiceImpl areaService, ProvinceServiceImpl provinceServiceImpl) {
        this.provinceService = provinceService;
        this.cityService = cityService;
        this.areaService = areaService;
        this.provinceServiceImpl = provinceServiceImpl;
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


    @GetMapping("/home/migrate_locations")
    public ResponseEntity<String> migrateLocation() {
        migrateLocationProvances();

        migrateLocationCity();
        migrateLocationAreas();
        return ResponseEntity.ok("ok");


    }


    // @GetMapping("/home/migrate_locations")
    public ResponseEntity<List<Province>> migrateLocationProvances() {
        WebClient webClient = WebClient.create("https://speedaf.com/oms/province/MA/queryList?countryCode=MA");
        List<Province> provinceDtoList = new ArrayList<>();
        Mono<Map<String, Object>> result = webClient.get()
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                });

        Map<String, Object> response = result.block();
        if (response.containsKey("data")) {
            JSONObject jsonData = new JSONObject(response);
            jsonData.getJSONArray("data").forEach(province -> {
                JSONObject provinceJson = (JSONObject) province;
                Province provinceDto = new Province();
                if (provinceJson.has("code")) {
                    provinceDto.setCode(provinceJson.getString("code"));
                }
                if (provinceJson.has("name")) {
                    provinceDto.setName(provinceJson.getString("name"));
                }
                if (provinceJson.has("countryCode")) {
                    provinceDto.setCountryCode(provinceJson.getString("countryCode"));
                }
                if (provinceJson.has("postcode")) {
                    provinceDto.setPostalCode(provinceJson.getString("postcode"));
                }
                provinceDtoList.add(provinceDto);
            });
        }


        return ResponseEntity.ok(provinceServiceImpl.saveAll(provinceDtoList));
    }

    //  @GetMapping("/home/migrate_locations_citys")
    public ResponseEntity<List<List<City>>> migrateLocationCity() {
        List<Province> provinces = provinceService.loadAll();
        List<List<City>> cityList = new ArrayList<>();
        provinces.stream().map(province -> {


            WebClient webClient = WebClient.create("https://speedaf.com/oms/city/"+province.getCode()+"/queryList?provinceCode=" + province.getCode());
            List<City> cityDtoList = new ArrayList<>();
            Mono<Map<String, Object>> result = webClient.get()
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                    });

            Map<String, Object> response = result.block();
            if (response.containsKey("data")) {
                JSONObject jsonData = new JSONObject(response);
                jsonData.getJSONArray("data").forEach(city -> {
                    JSONObject cityJson = (JSONObject) city;
                    City cityDto = new City();
                    if (cityJson.has("code")) {
                        cityDto.setCode(cityJson.getString("code"));
                    }
                    if (cityJson.has("name")) {
                        cityDto.setName(cityJson.getString("name"));
                    }
                    if (cityJson.has("countryCode")) {
                        cityDto.setCountryCode(cityJson.getString("countryCode"));
                    }
                    if (cityJson.has("postcode")) {
                        cityDto.setPostalCode(cityJson.getString("postcode"));
                    }

                    if (cityJson.has("provinceCode")) {
                        cityDto.setProvinceCode(province);
                    }

                    cityDtoList.add(cityDto);
                });
            }

            cityList.add(cityDtoList);



            return cityService.saveAll(cityDtoList);
        }).collect(Collectors.toList());


        return ResponseEntity.ok(cityList);

    }


    //  @GetMapping("/home/migrate_locations_areas")
    public ResponseEntity<List<List<Area>>> migrateLocationAreas() {

        List<City> cities = cityService.loadAll();
        List<List<Area>> areaList = new ArrayList<>();
        cities.stream().map(city -> {

            WebClient webClient = WebClient.create("https://speedaf.com/oms/area/" + city.getCode() + "/queryList?cityCode=" + city.getCode());
            List<Area> areaDtoList = new LinkedList<>();
            Mono<Map<String, Object>> result = webClient.get()
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {
                    });


            Map<String, Object> response = result.block();

            if (response.containsKey("data")) {
                JSONObject jsonData = new JSONObject(response);
                jsonData.getJSONArray("data").forEach(area -> {
                    JSONObject areaJson = (JSONObject) area;
                    Area areaDto = new Area();
                    if (areaJson.has("code")) {
                        areaDto.setCode(areaJson.getString("code"));
                    }
                    if (areaJson.has("name")) {
                        areaDto.setName(areaJson.getString("name"));
                    }
                    if (areaJson.has("postcode")) {
                        areaDto.setPostCode(areaJson.getString("postcode"));
                    }
                    if (areaJson.has("cityCode")) {
                        areaDto.setCityCode(city);
                    }

                    areaDtoList.add(areaDto);
                });


            }

            areaList.add(areaDtoList);

            return areaService.saveAll(areaDtoList);

        }).collect(Collectors.toList());

        return ResponseEntity.ok(areaList);
    }


}
