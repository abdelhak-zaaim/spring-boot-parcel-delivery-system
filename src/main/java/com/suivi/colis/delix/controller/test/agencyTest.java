/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 18:49
 *  * @modified : 04/05/2024, 18:49
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.controller.test;

import com.suivi.colis.delix.dto.response.MapsLocationPointDto;
import com.suivi.colis.delix.entity.Address;
import com.suivi.colis.delix.entity.Agency;
import com.suivi.colis.delix.model.MapsLocationPoint;
import com.suivi.colis.delix.service.Impl.AgencyServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class agencyTest {

    private final AgencyServiceImpl agencyService;

    public agencyTest(AgencyServiceImpl agencyService) {
        this.agencyService = agencyService;
    }

    @GetMapping("/test/agency/add")
    public ResponseEntity<Agency> addagencys() {
        // add agency for testing
        Agency agency = new Agency();
        agency.setAgencyName("CASA hub");
        agency.setAgencyContactNumber("0665458798");
        agency.setAgencyEmail("casa@test.com");

        Address address = new Address("3ayn dyab", "CASA", "casa-rabat", "20000", "Morocco");
            agency.setAgencyAddress(address);
        MapsLocationPoint mapsLocationPoint = new MapsLocationPoint(34.1865145, -6.1249762);
        agency.setLocationPoint(
                mapsLocationPoint);


        agencyService.saveAgency(agency);
        return ResponseEntity.ok(agency);
    }
}
