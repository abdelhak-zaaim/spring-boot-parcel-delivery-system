
/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 21:44
 *  * @modified : 04/05/2024, 21:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.controller.admin;

import com.fsdm.pfe.delix.dto.request.AgencyRequestDto;
import com.fsdm.pfe.delix.dto.response.AlertMessageDto;
import com.fsdm.pfe.delix.service.Impl.AgencyServiceImpl;
import com.fsdm.pfe.delix.util.Constants;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        model.addAttribute("csrf_token", csrfToken.getToken());

        return "admin/agency/add";
    }


    @PostMapping("/admin/agency/add")
    public ResponseEntity<AlertMessageDto> addAgency(@Valid AgencyRequestDto agencyRequestDto) {

        log.debug("AgencyRequestDto : {}", agencyRequestDto.toString());
        try {
            agencyService.saveAgency(agencyService.convertRequestDtoToEntity(agencyRequestDto));
        }catch (Exception e) {
            log.error("Error while saving agency : {}", e.getMessage());
            return ResponseEntity.ok(new AlertMessageDto(AlertMessageDto.AlertType.ERROR.name().toLowerCase(), e.getMessage()));
        }
        return ResponseEntity.ok(new AlertMessageDto(AlertMessageDto.AlertType.SUCCESS.name().toLowerCase(), "Agency added successfully"));

    }

    @GetMapping("/admin/agencies")
    public String getAgencies(Model model) {
        model.addAttribute(Constants.CURRENT_PAGE, "/admin/agencies");
        model.addAttribute("listDtoAgencies", agencyService.convertEntityListToResponseDtoList(agencyService.getAllAgencies()));
        return "admin/agency/listAgencies";
    }

    @PostMapping("/admin/agency/delete/{id}")
    public ResponseEntity<AlertMessageDto> deleteAgency(@PathVariable("id") Long id) {
        log.debug("RequestEntity : {}", id);
        try {
            agencyService.deleteAgency(id);
        }catch (Exception e) {
            log.error("Error while deleting agency : {}", e.getMessage());
            return ResponseEntity.ok(new AlertMessageDto(AlertMessageDto.AlertType.ERROR.name().toLowerCase(), e.getMessage()));
        }
        return ResponseEntity.ok(new AlertMessageDto(AlertMessageDto.AlertType.SUCCESS.name().toLowerCase(), "Agency deleted successfully"));
    }

}
