/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 16:11
 *  * @modified : 25/04/2024, 16:11
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.controllers;

import com.suivi.colis.suivicolis.models.Agency;
import com.suivi.colis.suivicolis.models.Customer;
import com.suivi.colis.suivicolis.models.Parcel;
import com.suivi.colis.suivicolis.models.PrivilegesGroup;
import com.suivi.colis.suivicolis.models.enums.ParcelStatus;
import com.suivi.colis.suivicolis.models.enums.ParcelType;
import com.suivi.colis.suivicolis.models.enums.Privilege;
import com.suivi.colis.suivicolis.services.ParcelService;
import com.suivi.colis.suivicolis.services.PrivilegesGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* actualy this is a test class
* we used it for testing pruposes
*
* todo : delete this class after testing
* */
@Controller
public class Test {

    @Autowired
    private PrivilegesGroupService privilegesGroupService;
    @Autowired
    private ParcelService paarcelService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @GetMapping("/loginSuccess")
    public ResponseEntity<String> loginSuccess() {
        return ResponseEntity.ok("You are logged in");
    }

    @GetMapping("/errorr")
    public ResponseEntity<String> error() {
        return new ResponseEntity<>("have a error", HttpStatus.OK);
    }


    @GetMapping("/test/addPriviligeGroup")
    public ResponseEntity<String> addPriviligeGroup() {
        List<Privilege> privileges = new ArrayList<>();
        privileges.add(Privilege.ADD_EMPLOYEE);
        privileges.add(Privilege.ADD_USER);
        privileges.add(Privilege.ADD_USER);
        privileges.add(Privilege.AGENCY_MANAGEMENT);
        try {
            privilegesGroupService.addPrivilegesGroup(new PrivilegesGroup("ADMIN", privileges));
        } catch (Exception e) {
            return new ResponseEntity<>("priviliges not added added"+e.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("priviliges was added", HttpStatus.OK);
    }

    @GetMapping("/test/addParcel")
    public ResponseEntity<String> addParcel() {
        // Create new Parcel object
        Parcel parcel = new Parcel();

// Set properties

        parcel.setHeight(10.0f);
        parcel.setWidth(20.0f);
        parcel.setWhight(30.0f);
        parcel.setStatus(ParcelStatus.IN_TRANSIT); // Assuming ParcelStatus is an enum with a value of NEW
        parcel.setType(ParcelType.DOCUMENT); // Assuming ParcelType is an enum with a value of TYPE1






        try {
            paarcelService.addParcel( parcel);
        } catch (Exception e) {
            return new ResponseEntity<>("parcel not added added"+e.toString(), HttpStatus.OK);
        }

        return new ResponseEntity<>("parcel was added", HttpStatus.OK);
    }
}
