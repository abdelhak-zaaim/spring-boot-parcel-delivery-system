
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
import com.suivi.colis.delix.entity.Agency;
import com.suivi.colis.delix.service.Impl.AgencyServiceImpl;
import com.suivi.colis.delix.util.Constants;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AgencyController {
    private static final Logger log = LoggerFactory.getLogger(AgencyController.class);
    private final AgencyServiceImpl agencyService;

    public AgencyController(AgencyServiceImpl agencyService) {
        this.agencyService = agencyService;
    }
    @GetMapping("/admin/agency/add")
   public String addAgency(Model model){
       model.addAttribute(Constants.CURRENT_PAGE, "/agency/add");
        return "admin/agency/add";
    }


    @PostMapping( "/admin/agency/add")
    public String addAgency( AgencyRequestDto agencyRequestDto ){
        log.debug("AgencyRequestDto : {}", agencyRequestDto.toString());
        agencyService.saveAgency(agencyService.convertDtoToEntity(agencyRequestDto));
        return "redirect:/admin/agency/list";
    }
}
