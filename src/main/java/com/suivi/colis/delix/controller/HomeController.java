/*
 * **
 *  * @project : DeliX
 *  * @created : 24/04/2024, 17:23
 *  * @modified : 24/04/2024, 17:23
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.controller;

import com.suivi.colis.delix.dto.response.AgencyResponseDto;
import com.suivi.colis.delix.entity.Agency;
import com.suivi.colis.delix.service.Impl.AgencyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Controller
public class HomeController {

    private final AgencyServiceImpl agencyService;

    public HomeController(AgencyServiceImpl agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping("/")
    public String home() {

        return "home/index";
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

}
