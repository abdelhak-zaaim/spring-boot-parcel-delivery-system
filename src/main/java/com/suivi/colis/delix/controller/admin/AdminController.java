
/*
 * **
 *  * @project : DeliX
 *  * @created : 04/05/2024, 20:27
 *  * @modified : 04/05/2024, 20:27
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.controller.admin;

import com.suivi.colis.delix.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping({"/admin/", "/admin"})
    public String admin(Model model) {
        model.addAttribute(Constants.CURRENT_PAGE, "/admin");
        return "admin/index";
    }

}
