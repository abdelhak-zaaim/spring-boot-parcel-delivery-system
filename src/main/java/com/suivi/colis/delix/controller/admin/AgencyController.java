
/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 21:44
 *  * @modified : 04/05/2024, 21:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.controller.admin;

import com.suivi.colis.delix.dto.request.AgencyRequestDto;
import com.suivi.colis.delix.service.Impl.AgencyServiceImpl;
import com.suivi.colis.delix.util.Constants;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Slf4j
@Controller
public class AgencyController {
    private final AgencyServiceImpl agencyService;

    public AgencyController(AgencyServiceImpl agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping("/admin/agency/add")
    public String addAgency(Model model, CsrfToken csrfToken) {
        model.addAttribute("_csrf", csrfToken.getToken());



        return "admin/agency/add";
    }


    @PostMapping("/admin/agency/add")
    public String addAgency(@Valid AgencyRequestDto agencyRequestDto) {
        log.debug("AgencyRequestDto : {}", agencyRequestDto.toString());
        agencyService.saveAgency(agencyService.convertRequestDtoToEntity(agencyRequestDto));
        return "redirect:/admin/agency/list";
    }
}
