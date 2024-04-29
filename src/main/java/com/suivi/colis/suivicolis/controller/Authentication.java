/*
 * **
 *  * @project : SuiviColis
 *  * @created : 24/04/2024, 17:08
 *  * @modified : 24/04/2024, 17:08
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.controller;

import org.springframework.stereotype.Controller;

@Controller
public class Authentication {




    public record LoginRequest(String username, String password) {
    }


}
