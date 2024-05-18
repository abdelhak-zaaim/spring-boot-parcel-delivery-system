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


import com.fsdm.pfe.delix.dto.request.UpdateProfileRequestDto;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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




    private final AgencyServiceImpl agencyService;
    private final ProvinceServiceImpl provinceServiceImpl;
    private final ProjectInfoAutoConfiguration projectInfoAutoConfiguration;

    public HomeController( AgencyServiceImpl agencyService, ProvinceServiceImpl provinceServiceImpl, ProjectInfoAutoConfiguration projectInfoAutoConfiguration) {

        this.agencyService = agencyService;
        this.provinceServiceImpl = provinceServiceImpl;
        this.projectInfoAutoConfiguration = projectInfoAutoConfiguration;
    }

    @GetMapping("/")
    public String home() {
        return "home/index";
    }




    @GetMapping("/home/get_location_agencys")
    public ResponseEntity<List<AgencyResponseDto>> getLocationAgency() {
        log.debug("Getting all agencies");
        List<Agency> agencies = agencyService.getAllAgencies();
        return ResponseEntity.ok(agencies.stream()
                .map(AgencyResponseDto::new)
                .collect(Collectors.toList()));
    }



}