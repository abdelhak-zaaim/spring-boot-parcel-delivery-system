/*
 * **
 *  * @project : SuiviColis
 *  * @created : 24/04/2024, 17:08
 *  * @modified : 24/04/2024, 17:08
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.controllers;

import com.suivi.colis.suivicolis.services.PrivilegesGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Authentication {
    @Autowired
    private PrivilegesGroupService privilegesGroupService;



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




}
