/*
 * **
 *  * @project : DeliX
 *  * @created : 24/04/2024, 17:23
 *  * @modified : 24/04/2024, 17:23
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.controller;


import com.fsdm.pfe.delix.dto.response.AgencyResponseDto;
import com.fsdm.pfe.delix.entity.Agency;
import com.fsdm.pfe.delix.entity.location.Area;
import com.fsdm.pfe.delix.entity.location.City;
import com.fsdm.pfe.delix.entity.location.Province;
import com.fsdm.pfe.delix.service.Impl.AgencyServiceImpl;
import com.fsdm.pfe.delix.service.Impl.location.AreaServiceImpl;
import com.fsdm.pfe.delix.service.Impl.location.CityServiceImpl;
import com.fsdm.pfe.delix.service.Impl.location.ProvinceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class HomeController {

    private final ProvinceServiceImpl provinceService;
    private final AreaServiceImpl areaService;
    private final CityServiceImpl cityService;


    private final AgencyServiceImpl agencyService;
    private final ProvinceServiceImpl provinceServiceImpl;
    private final ProjectInfoAutoConfiguration projectInfoAutoConfiguration;

    public HomeController(ProvinceServiceImpl provinceService, AreaServiceImpl areaService, CityServiceImpl cityService, AgencyServiceImpl agencyService, ProvinceServiceImpl provinceServiceImpl, ProjectInfoAutoConfiguration projectInfoAutoConfiguration) {
        this.provinceService = provinceService;
        this.areaService = areaService;
        this.cityService = cityService;
        this.agencyService = agencyService;
        this.provinceServiceImpl = provinceServiceImpl;
        this.projectInfoAutoConfiguration = projectInfoAutoConfiguration;
    }

    @GetMapping("/")
    public String home() {

        return "home/index"; // for tseting ; todo : make this "home/index"
    }


    @GetMapping("/home")
    public RedirectView dash() {
        // this is for testing only , todo : remove this
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return new RedirectView("http://www.google.com");
        } else {
            return new RedirectView("/login");
        }
    }


    @GetMapping("/home/get_location_agencys")
    public ResponseEntity<List<AgencyResponseDto>> getLocationAgency() {
        log.debug("Getting all agencies");
        List<Agency> agencies = agencyService.getAllAgencies();
        return ResponseEntity.ok(agencies.stream()
                .map(AgencyResponseDto::new)
                .collect(Collectors.toList()));
    }


    @GetMapping("/home/migrate_locations")
    public ResponseEntity<List<Province>> migrateLocation() {
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

    @GetMapping("/home/migrate_locations_citys")
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


    @GetMapping("/home/migrate_locations_areas")
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