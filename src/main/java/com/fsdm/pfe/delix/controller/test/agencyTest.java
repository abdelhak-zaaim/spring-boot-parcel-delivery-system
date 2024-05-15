/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 18:49
 *  * @modified : 04/05/2024, 18:49
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.controller.test;

import com.fsdm.pfe.delix.entity.Address;
import com.fsdm.pfe.delix.entity.Agency;
import com.fsdm.pfe.delix.model.MapsLocationPoint;
import com.fsdm.pfe.delix.service.Impl.AgencyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class agencyTest {

    private final AgencyServiceImpl agencyService;

    public agencyTest(AgencyServiceImpl agencyService) {
        this.agencyService = agencyService;
    }


}
